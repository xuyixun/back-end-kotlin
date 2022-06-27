package com.xyx.punishment.domain.dto

import com.google.common.base.Strings

data class FileRelationDto(val uuid: String = "", val fileUUid: Set<String>?) {
    fun check() = Strings.isNullOrEmpty(uuid) || fileUUid == null || fileUUid.isEmpty()
}
