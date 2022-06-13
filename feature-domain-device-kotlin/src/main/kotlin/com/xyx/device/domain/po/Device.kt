package com.xyx.device.domain.po

import com.xyx.common.domain.po.CommonPoEnabled
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Device(
    var uid: String,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn var deviceType: DeviceType?,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn var deviceBrand: DeviceBrand?,
    var deleted: Boolean = false,
    var deletedTime: LocalDateTime? = null,
    @Id @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid2") var uuid: String = "",
    @CreationTimestamp val createTime: LocalDateTime = LocalDateTime.now()
) : CommonPoEnabled {
    override var enabled: Boolean = false
}