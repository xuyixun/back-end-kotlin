package com.xyx.structure.domain.dto

import com.google.common.base.Strings

data class StructureFloorSearchDto(val structureUuid: String = "", var hideDisabled: Boolean = true) {
    fun check() = Strings.isNullOrEmpty(structureUuid)
}
