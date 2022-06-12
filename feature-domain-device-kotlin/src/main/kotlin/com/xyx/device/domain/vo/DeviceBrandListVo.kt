package com.xyx.device.domain.vo

import com.xyx.device.domain.po.DeviceBrand

data class DeviceBrandListVo(val uuid: String, val name: String, val enable: Boolean) {
    companion object {
        fun vo(entity: DeviceBrand) = DeviceBrandListVo(entity.uuid, entity.name, entity.enabled)
    }
}
