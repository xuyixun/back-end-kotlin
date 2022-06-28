package com.xyx.structure.domain.dto.search

import com.google.common.base.Strings

data class StructureFloorSearchDto(val structureUuid: String = "", val hideDisabled: Boolean = true) {
    fun check() = Strings.isNullOrEmpty(structureUuid)
}
