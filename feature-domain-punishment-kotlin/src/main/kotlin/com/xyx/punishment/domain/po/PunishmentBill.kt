package com.xyx.punishment.domain.po

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class PunishmentBill(
    val name: String,
    val longitude: String,
    val latitude: String,
    val time: LocalDateTime,
    val type: PunishmentBillType,
    val mode: PunishmentBillMode,
    @Id @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid2") var uuid: String = "",
    @CreationTimestamp val createTime: LocalDateTime = LocalDateTime.now()
)

enum class PunishmentBillType {
    fishing
}


enum class PunishmentBillMode {
    fine
}
