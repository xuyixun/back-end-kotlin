package com.xyx.device.domain.vo

import com.xyx.device.domain.po.Device

data class DeviceDetailVo(
    val uuid: String, val uid: String, val brandName: String, val typeName: String,
    val model: String
) {
    companion object {
        fun vo(entity: Device) = DeviceDetailVo(entity.uuid, entity.uid, entity.brand.name, entity.type.name, entity.model)
    }
}
