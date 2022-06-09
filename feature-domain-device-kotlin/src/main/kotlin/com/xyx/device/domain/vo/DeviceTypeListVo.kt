package com.xyx.device.domain.vo

import com.xyx.device.domain.po.DeviceType

data class DeviceTypeListVo(val uuid: String, val name: String, val enable: Boolean) {
    companion object {
        fun vo(entity: DeviceType): DeviceTypeListVo = DeviceTypeListVo(entity.uuid, entity.name, entity.enabled)
    }
}
