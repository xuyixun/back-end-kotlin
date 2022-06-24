package com.xyx.device.domain.dto.save

import com.google.common.base.Strings

data class DeviceBrandSaveDto(val name: String = "") {
    fun check() = Strings.isNullOrEmpty(name)
}