package com.xyx.structure.domain.dto

import com.google.common.base.Strings

data class StructureUpdateDto(
    val uuid: String?, val name: String?, val longitude: String?, val latitude: String?
) {
    fun check() = Strings.isNullOrEmpty(uuid) || Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(longitude) || Strings.isNullOrEmpty(latitude)
}