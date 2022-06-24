package com.xyx.punishment.domain.dto

import com.google.common.base.Strings
import com.xyx.punishment.domain.po.PunishmentBillMode
import com.xyx.punishment.domain.po.PunishmentBillType
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

data class PunishmentBillSaveDto(
    val name: String = "",
    val longitude: String = "",
    val latitude: String = "",
    @ApiModelProperty(value = "时间(yyyy-mm-dd hh:MM:ss)") val time: String = "",
    @ApiModelProperty(value = "类型") val type: PunishmentBillType? = null,
    @ApiModelProperty(value = "处理方式") val mode: PunishmentBillMode? = null,
    @ApiModelProperty(value = "罚款金额", example = "0") val amount: BigDecimal? = null,
    @ApiModelProperty(value = "违法者姓名") val offenderName: String? = null,
    val offenderIdCard: String? = null,
    val offenderPhoneNumber: String? = null
) {
    fun check() = Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(longitude) || Strings.isNullOrEmpty(latitude) || Strings.isNullOrEmpty(time) || type == null || mode == null
}
