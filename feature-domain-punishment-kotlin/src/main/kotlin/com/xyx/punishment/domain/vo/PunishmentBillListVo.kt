package com.xyx.punishment.domain.vo

import com.xyx.punishment.domain.po.PunishmentBill

data class PunishmentBillListVo(val uuid: String) {
    companion object {
        fun vo(entity: PunishmentBill) = PunishmentBillListVo(entity.uuid)
    }
}
