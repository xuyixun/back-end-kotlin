package com.xyx.punishment.domain.dto

import com.google.common.base.Strings

data class FileDeleteAllDto(val uuid: String = "") {
    fun check() = Strings.isNullOrEmpty(uuid)
}
