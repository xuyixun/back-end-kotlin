package com.xyx.device.domain.vo

import com.xyx.device.domain.po.Device

data class DeviceListVo(val uuid: String, val enable: Boolean, val uid: String) {
    companion object {
        fun vo(entity: Device) = DeviceListVo(entity.uuid, entity.enabled, entity.uid)
    }
}
