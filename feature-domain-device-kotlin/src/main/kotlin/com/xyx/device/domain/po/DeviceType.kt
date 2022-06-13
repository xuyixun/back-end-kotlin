package com.xyx.device.domain.po

import com.xyx.common.domain.po.CommonPoDeleted
import com.xyx.common.domain.po.CommonPoEnabled
import javax.persistence.Entity

@Entity
data class DeviceType(var name: String) : CommonPoDeleted(), CommonPoEnabled {
    override var enabled: Boolean = false

    companion object {
        fun create(uuid: String) = DeviceType("").apply { this.uuid = uuid }
    }
}
