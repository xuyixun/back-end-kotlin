package com.xyx.device.domain.vo

import com.xyx.device.domain.po.Device

data class DeviceListVo(val uuid: String, val uid: String, val enable: Boolean) {
    companion object {
        fun vo(entity: Device) = DeviceListVo(entity.uuid, entity.uid, entity.enabled)
    }
}
