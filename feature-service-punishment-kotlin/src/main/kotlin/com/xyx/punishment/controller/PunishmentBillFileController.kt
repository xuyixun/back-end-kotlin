package com.xyx.punishment.controller

import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.file.domain.po.CommonFile
import com.xyx.file.domain.po.CommonFileType
import com.xyx.file.func.WatermarkFunc
import com.xyx.punishment.domain.dto.FileDeleteAllDto
import com.xyx.punishment.domain.dto.FileDeleteDto
import com.xyx.punishment.domain.dto.FileRelationDto
import com.xyx.punishment.domain.po.PunishmentBill
import com.xyx.punishment.domain.po.PunishmentBillFile
import com.xyx.punishment.domain.repository.PunishmentBillFileRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.data.domain.Sort
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.time.format.DateTimeFormatter

@Api(tags = ["处罚-处罚单文件"])
@RestController
@RequestMapping("api/punishment_bill_file")
class PunishmentBillFileController(private val punishmentBillFileRepository: PunishmentBillFileRepository, private val watermarkFunc: WatermarkFunc) {
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

    @Transactional
    @ApiOperation(value = "预上传关联项目")
    @PostMapping(value = ["v1/relation"])
    fun relationItemSf(dto: FileRelationDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        dto.fileUUid.forEach { v ->
            if (punishmentBillFileRepository.existsByPunishmentBillUuidAndCommonFileUuid(dto.uuid, v)
                    .not()
            ) {
                this.punishmentBillFileRepository.save(
                    PunishmentBillFile(PunishmentBill.create(dto.uuid), CommonFile.create(v))
                )
            }
        }
        return returnSuccess()
    }

    @PostMapping(value = ["v1/watermark/{uuid}"])
    suspend fun watermark(@PathVariable uuid: String): String {
        punishmentBillFileRepository.findByPunishmentBillUuidAndCommonFileType(uuid, CommonFileType.IMAGE, Sort.by("createTime"))
            .forEach {
                watermarkFunc.text(
                    it.commonFile.uuid, "${it.punishmentBill.longitude},${it.punishmentBill.latitude}", it.punishmentBill.time.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"))
                )
            }
        return "success"
    }

    @GetMapping("test001")
    suspend fun test001(): String {
        repeat(10) {
            delay(1000)
            println(it)
        }
        return "success"
    }

    @GetMapping("test002")
    suspend fun test002(): String = coroutineScope {
        repeat(10) {
            launch {
                delay(1000)
                println(it)
            }
        }
        "success"
    }


    @GetMapping("test003")
    suspend fun test003(): String = coroutineScope {
        val channel = Channel<Int>()
        launch {
            repeat(10) {
                delay(1000)
                channel.send(it)
            }
        }
        repeat(10) { println(channel.receive()) }
        "success"
    }
}