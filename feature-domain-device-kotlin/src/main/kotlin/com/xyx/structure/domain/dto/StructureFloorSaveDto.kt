package com.xyx.structure.domain.dto

import com.google.common.base.Strings

data class StructureFloorSaveDto(val structureUuid: String = "", val number: Byte = 1, val mapImageUuid: String = "") {
    fun check() = Strings.isNullOrEmpty(structureUuid)
}