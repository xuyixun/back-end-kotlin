package com.xyx.device.domain.dto

import com.google.common.base.Strings

data class DeviceUpdateDto(val uuid: String = "", val uid: String = "", val deviceTypeUuid: String = "", val deviceBrandUuid: String = "", val model: String) {
    fun check() = Strings.isNullOrEmpty(uuid) || Strings.isNullOrEmpty(uid) || Strings.isNullOrEmpty(deviceTypeUuid) || Strings.isNullOrEmpty(deviceBrandUuid) || Strings.isNullOrEmpty(model)
}
