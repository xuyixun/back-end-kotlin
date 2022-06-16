package com.xyx.punishment.domain.vo

import com.xyx.punishment.domain.po.PunishmentBill
import com.xyx.punishment.domain.po.PunishmentBillMode
import com.xyx.punishment.domain.po.PunishmentBillType
import java.time.format.DateTimeFormatter

data class PunishmentBillListVo(
    val uuid: String, val name: String, val time: String, val type: PunishmentBillType, val mode: PunishmentBillMode, val offenderName: String?
) {
    companion object {
        fun vo(entity: PunishmentBill) =
            PunishmentBillListVo(entity.uuid, entity.name, entity.time.format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")), entity.type, entity.mode, entity.offenderName)
    }
}
