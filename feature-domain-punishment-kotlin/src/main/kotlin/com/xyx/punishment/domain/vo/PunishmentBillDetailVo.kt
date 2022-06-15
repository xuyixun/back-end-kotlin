package com.xyx.punishment.domain.vo

import com.xyx.punishment.domain.po.PunishmentBill

data class PunishmentBillDetailVo(val uuid: String) {
    companion object {
        fun vo(entity: PunishmentBill) = PunishmentBillDetailVo(entity.uuid)
    }
}
