package com.xyx.punishment.domain.dto

import com.google.common.base.Strings

data class FileDeleteDto(val uuid: String = "", val fileUUid: String = "") {
    fun check() = Strings.isNullOrEmpty(uuid) || Strings.isNullOrEmpty(fileUUid)
}