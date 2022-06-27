package com.xyx.structure.domain.dto

import com.google.common.base.Strings

data class StructureFloorUpdateDto(val uuid: String = "", val number: Byte = 0, val mapImageUuid: String = "") {
    fun check() = Strings.isNullOrEmpty(uuid)
}
