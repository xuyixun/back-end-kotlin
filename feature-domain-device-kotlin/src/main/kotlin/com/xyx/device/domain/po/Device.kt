package com.xyx.device.domain.po

import com.xyx.common.domain.po.CommonPoDeleted
import com.xyx.common.domain.po.CommonPoEnabled
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Device(
    var uid: String, @ManyToOne(fetch = FetchType.LAZY) @JoinColumn var deviceType: DeviceType
) : CommonPoDeleted(), CommonPoEnabled {
    override var enabled: Boolean = false
}