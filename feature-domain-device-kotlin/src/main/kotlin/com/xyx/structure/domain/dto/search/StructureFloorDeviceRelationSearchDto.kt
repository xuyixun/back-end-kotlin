package com.xyx.structure.domain.dto.search

import com.xyx.common.domain.dto.CommonPageDto

data class StructureFloorDeviceRelationSearchDto(
    val structureFloorUuid: String = "",
    val structureUuid: String = "",
    val uid: String = "",
    val deviceTypeUuid: String = "",
    val hideDisabled: Boolean = true
) : CommonPageDto()
