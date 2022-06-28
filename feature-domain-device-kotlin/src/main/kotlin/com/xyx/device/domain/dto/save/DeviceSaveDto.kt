package com.xyx.device.domain.dto.save

import com.google.common.base.Strings
import io.swagger.annotations.ApiModelProperty

data class DeviceSaveDto(
    val uid: String = "",
    @ApiModelProperty(value = "类型") val deviceTypeUuid: String = "",
    @ApiModelProperty(value = "品牌") val deviceBrandUuid: String = "",
    @ApiModelProperty(value = "型号") val model: String = ""
) {
    fun check() = Strings.isNullOrEmpty(uid) || Strings.isNullOrEmpty(deviceTypeUuid) || Strings.isNullOrEmpty(deviceBrandUuid) || Strings.isNullOrEmpty(model)
}
