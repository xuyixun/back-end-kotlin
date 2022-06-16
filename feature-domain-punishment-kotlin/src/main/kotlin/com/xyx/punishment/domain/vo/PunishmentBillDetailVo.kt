package com.xyx.punishment.domain.vo

import com.xyx.file.domain.vo.CommonFileSimpleVo
import com.xyx.punishment.domain.po.PunishmentBill
import com.xyx.punishment.domain.po.PunishmentBillMode
import com.xyx.punishment.domain.po.PunishmentBillType
import com.xyx.punishment.domain.repository.PunishmentBillFileRepository
import org.springframework.data.domain.Sort
import java.math.BigDecimal
import java.time.format.DateTimeFormatter

data class PunishmentBillDetailVo(
    val uuid: String,
    val name: String,
    val longitude: String,
    val latitude: String,
    val time: String,
    val type: PunishmentBillType,
    val mode: PunishmentBillMode,
    val amount: BigDecimal?,
    val offenderName: String?,
    val offenderIdCard: String?,
    val offenderPhoneNumber: String?,
    val files: Set<CommonFileSimpleVo>?
) {
    companion object {
        fun vo(entity: PunishmentBill, r: PunishmentBillFileRepository) = PunishmentBillDetailVo(
            entity.uuid,
            entity.name,
            entity.longitude,
            entity.latitude,
            entity.time.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")),
            entity.type,
            entity.mode,
            entity.amount,
            entity.offenderName,
            entity.offenderIdCard,
            entity.offenderPhoneNumber,
            r.findByPunishmentBillUuid(entity.uuid, Sort.by("createTime"))
                .map { CommonFileSimpleVo.vo(it.commonFile) }
                .toSet()
        )
    }
}
