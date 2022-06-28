package com.xyx.structure.domain.dto.save

import com.google.common.base.Strings

data class StructureFloorDeviceRelationSaveDto(
    val structureFloorUuid: String = "", val deviceUuid: String = "", val pointX: Short = 0, val pointY: Short = 0
) {
    fun check() = Strings.isNullOrEmpty(structureFloorUuid) || Strings.isNullOrEmpty(deviceUuid)
}
