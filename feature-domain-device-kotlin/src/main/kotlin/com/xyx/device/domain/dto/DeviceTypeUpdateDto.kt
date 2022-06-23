package com.xyx.device.domain.dto

import com.google.common.base.Strings

data class DeviceTypeUpdateDto(val uuid: String?, val name: String?) {
    fun check() = Strings.isNullOrEmpty(uuid) || Strings.isNullOrEmpty(name)
}