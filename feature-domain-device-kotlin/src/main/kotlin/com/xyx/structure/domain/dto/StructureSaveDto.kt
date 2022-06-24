package com.xyx.structure.domain.dto

import com.google.common.base.Strings

data class StructureSaveDto(
    val name: String = "", val longitude: String = "", val latitude: String = ""
) {
    fun check() = Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(longitude) || Strings.isNullOrEmpty(latitude)
}