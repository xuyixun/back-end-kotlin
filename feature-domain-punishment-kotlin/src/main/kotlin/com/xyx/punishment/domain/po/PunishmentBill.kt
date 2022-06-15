package com.xyx.punishment.domain.po

import com.xyx.common.domain.po.CommonPo
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
data class PunishmentBill(
    val name: String,
    val longitude: String,
    val latitude: String,
    val time: LocalDateTime,
    @Enumerated(EnumType.STRING) val type: PunishmentBillType,
    @Enumerated(EnumType.STRING) val mode: PunishmentBillMode,
    val amount: BigDecimal? = null
) : CommonPo() {
    companion object {
        fun create(uuid: String) = PunishmentBill("", "", "", LocalDateTime.now(), PunishmentBillType.FISHING, PunishmentBillMode.FINE).apply { this.uuid = uuid }
    }
}

enum class PunishmentBillType {
    FISHING
}


enum class PunishmentBillMode {
    FINE
}
