package com.xyx.punishment.domain.dto

import com.xyx.punishment.domain.po.PunishmentBillMode
import com.xyx.punishment.domain.po.PunishmentBillType
import java.math.BigDecimal

data class PunishmentBillSaveDto(
    val name: String, val longitude: String, val latitude: String, val time: String, val type: PunishmentBillType, val mode: PunishmentBillMode, val amount: BigDecimal? = null
)
