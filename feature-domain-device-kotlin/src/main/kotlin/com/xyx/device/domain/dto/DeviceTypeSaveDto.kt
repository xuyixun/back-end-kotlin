package com.xyx.device.domain.dto

import com.google.common.base.Strings

data class DeviceTypeSaveDto(val name: String) {
    fun check() = Strings.isNullOrEmpty(name)
}
