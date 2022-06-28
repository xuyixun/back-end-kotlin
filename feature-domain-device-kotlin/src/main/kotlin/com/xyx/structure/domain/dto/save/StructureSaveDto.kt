package com.xyx.structure.domain.dto.save

import com.google.common.base.Strings

data class StructureSaveDto(
    val name: String = "", val longitude: String = "", val latitude: String = "", val mapImageUuid: String = ""
) {
    fun check() = Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(longitude) || Strings.isNullOrEmpty(latitude)
}