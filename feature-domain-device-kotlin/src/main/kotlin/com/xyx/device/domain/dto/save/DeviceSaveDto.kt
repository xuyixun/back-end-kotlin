package com.xyx.device.domain.dto.save

import com.google.common.base.Strings

data class DeviceSaveDto(val uid: String?, val deviceTypeUuid: String?, val deviceBrandUuid: String?) {
    fun check() = Strings.isNullOrEmpty(uid) || Strings.isNullOrEmpty(deviceTypeUuid) || Strings.isNullOrEmpty(deviceBrandUuid)
}
