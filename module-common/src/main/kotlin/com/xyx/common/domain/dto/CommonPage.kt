package com.xyx.common.domain.dto

import io.swagger.annotations.ApiModelProperty

data class CommonPage(
    @ApiModelProperty(value = "页码", example = "0") var page: Int = 0,
    @ApiModelProperty(value = "数量", example = "0") var size: Int = 0
)