package com.xyx.punishment.controller

import com.xyx.common.func.Return
import com.xyx.common.func.returnSuccess
import com.xyx.punishment.domain.dto.PunishmentBillSaveDto
import com.xyx.punishment.domain.dto.PunishmentBillSearchDto
import com.xyx.punishment.domain.po.PunishmentBill
import com.xyx.punishment.domain.repository.PunishmentBillRepository
import com.xyx.punishment.domain.repository.query
import com.xyx.punishment.domain.vo.PunishmentBillListVo
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Api(tags = ["处罚-处罚单"])
@RestController
@RequestMapping("api/punishment/punishment_bill")
class PunishmentBillController(private val punishmentBillRepository: PunishmentBillRepository) {
    @GetMapping("v1")
    fun queryAll(dto: PunishmentBillSearchDto) = returnSuccess(punishmentBillRepository.query(dto, emptyArray(), emptyArray())
        .map { PunishmentBillListVo.vo(it) })

    @PostMapping("v1")
    fun save(dto: PunishmentBillSaveDto): Return = returnSuccess(
        punishmentBillRepository.save(
            PunishmentBill(
                dto.name, dto.longitude, dto.latitude, LocalDateTime.parse(dto.time, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")), dto.type, dto.mode, dto.amount
            )
        ).uuid
    )
}