package com.xyx.device.domain.po

import com.xyx.common.domain.po.CommonPo
import com.xyx.common.domain.po.CommonPoDeleted
import com.xyx.common.domain.po.CommonPoEnabled
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Device(
    var uid: String,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn var deviceType: DeviceType?,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn var deviceBrand: DeviceBrand?
) : CommonPo(), CommonPoDeleted, CommonPoEnabled {
    override var deleted: Boolean = false
    override var deletedTime: LocalDateTime? = null
    override var enabled: Boolean = false
}