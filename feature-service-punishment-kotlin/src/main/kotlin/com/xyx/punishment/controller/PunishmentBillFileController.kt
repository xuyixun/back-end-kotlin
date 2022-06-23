package com.xyx.punishment.controller

import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.file.domain.po.CommonFile
import com.xyx.file.func.WatermarkFunc
import com.xyx.punishment.domain.dto.FileDeleteAllDto
import com.xyx.punishment.domain.dto.FileDeleteDto
import com.xyx.punishment.domain.dto.FileRelationDto
import com.xyx.punishment.domain.po.PunishmentBill
import com.xyx.punishment.domain.po.PunishmentBillFile
import com.xyx.punishment.domain.repository.PunishmentBillFileRepository
import com.xyx.punishment.rabbitmq.RabbitMqService
import com.xyx.punishment.rabbitmq.RabbitMqService.Companion.RABBITMQ_EXCHANGE_PUNISHMENT_BILL_FILE_WATERMARK
import com.xyx.punishment.rabbitmq.RabbitMqService.Companion.RABBITMQ_KEY_COMMON
import com.xyx.punishment.rabbitmq.RabbitMqService.Companion.RABBITMQ_QUEUE_PUNISHMENT_BILL_FILE_WATERMARK
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.amqp.rabbit.annotation.Exchange
import org.springframework.amqp.rabbit.annotation.Queue
import org.springframework.amqp.rabbit.annotation.QueueBinding
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.time.format.DateTimeFormatter

@Api(tags = ["处罚-处罚单文件"])
@RestController
@RequestMapping("api/punishment_bill_file")
class PunishmentBillFileController(private val punishmentBillFileRepository: PunishmentBillFileRepository, private val rabbitMqService: RabbitMqService, private val watermarkFunc: WatermarkFunc) {
    @Transactional
    @ApiOperation(value = "项目图片关联删除-单个")
    @DeleteMapping(value = ["v1/delete"])
    fun delete(dto: FileDeleteDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        this.punishmentBillFileRepository.deleteByPunishmentBillUuidAndCommonFileUuid(dto.uuid, dto.fileUUid)
        return returnSuccess()
    }

    @Transactional
    @ApiOperation(value = "项目图片关联删除-所有")
    @DeleteMapping(value = ["v1/delete_all"])
    fun deleteAll(dto: FileDeleteAllDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        this.punishmentBillFileRepository.deleteByPunishmentBillUuid(dto.uuid)
        return returnSuccess()
    }

    @ApiOperation(value = "预上传关联项目")
    @PostMapping(value = ["v1/relation"])
    fun relationItemSf(dto: FileRelationDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        dto.fileUUid.forEach { v ->
            if (punishmentBillFileRepository.existsByPunishmentBillUuidAndCommonFileUuidAndWatermarkImgFalse(dto.uuid, v)
                    .not() && punishmentBillFileRepository.existsByPunishmentBillUuidAndCommonFileOriginalImgUuidAndWatermarkImgTrue(dto.uuid, v)
                    .not()
            ) {
                this.punishmentBillFileRepository.save(
                    PunishmentBillFile(PunishmentBill.create(dto.uuid), CommonFile.create(v))
                )
                    .apply {
                        rabbitMqService.punishmentBillFileWatermark(uuid)
                    }
            }
        }
        return returnSuccess()
    }

    @RabbitListener(
        bindings = [QueueBinding(
            value = Queue(name = RABBITMQ_QUEUE_PUNISHMENT_BILL_FILE_WATERMARK), exchange = Exchange(name = RABBITMQ_EXCHANGE_PUNISHMENT_BILL_FILE_WATERMARK), key = [RABBITMQ_KEY_COMMON]
        )]
    )
    fun watermark(@PathVariable uuid: String) {
        punishmentBillFileRepository.findById(uuid)
            .ifPresent {
                val bill = it.punishmentBill
                val s = watermarkFunc.text(
                    it.commonFile, "${bill.longitude},${bill.latitude}", bill.time.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"))
                )
                this.punishmentBillFileRepository.updateWatermark(it.uuid, s)
            }
    }
}