package com.xyx.structure.domain.dto.search

import com.xyx.common.domain.dto.CommonPageDto

data class StructureSearchDto(val name: String = "", val hideDisabled: Boolean = true) : CommonPageDto()

