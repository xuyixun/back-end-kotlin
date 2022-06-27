package com.xyx.punishment.controller

import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.punishment.domain.dto.PunishmentBillSaveDto
import com.xyx.punishment.domain.dto.PunishmentBillSearchDto
import com.xyx.punishment.domain.po.PunishmentBill
import com.xyx.punishment.domain.repository.PunishmentBillFileRepository
import com.xyx.punishment.domain.repository.PunishmentBillRepository
import com.xyx.punishment.domain.repository.query
import com.xyx.punishment.domain.vo.PunishmentBillDetailVo
import com.xyx.punishment.domain.vo.PunishmentBillListVo
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Api(tags = ["处罚-处罚单"])
@RestController
@RequestMapping("api/punishment_bill")
class PunishmentBillController(
    private val punishmentBillRepository: PunishmentBillRepository,
    private val punishmentBillFileRepository: PunishmentBillFileRepository,
    private val punishmentBillFileController: PunishmentBillFileController
) {
    @GetMapping("v1")
    fun queryAll(dto: PunishmentBillSearchDto) = returnSuccess(punishmentBillRepository.query(dto, emptyArray(), emptyArray())
        .map { PunishmentBillListVo.vo(it) })

    @PostMapping("v1")
    fun save(@RequestBody dto: PunishmentBillSaveDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        return returnSuccess(
            punishmentBillRepository.save(
                PunishmentBill(
                    dto.name,
                    dto.longitude,
                    dto.latitude,
                    LocalDateTime.parse(dto.time, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")),
                    dto.type!!,
                    dto.mode!!,
                    dto.amount,
                    dto.offenderName,
                    dto.offenderIdCard,
                    dto.offenderPhoneNumber
                )
            )
                .apply {
                    if (dto.fileUUid != null) {
                        punishmentBillFileController.relation(uuid, dto.fileUUid!!)
                    }
                }.uuid
        )
    }

    @GetMapping("v1/{uuid}")
    fun detail(@PathVariable uuid: String): Return {
        val o = this.punishmentBillRepository.findById(uuid)
        if (o.isPresent) {
            return returnSuccess(PunishmentBillDetailVo.vo(o.get(), punishmentBillFileRepository))
        }
        return returnSuccess()
    }
}