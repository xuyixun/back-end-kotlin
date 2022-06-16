package com.xyx.punishment.domain.dto

import com.xyx.punishment.domain.po.PunishmentBillMode
import com.xyx.punishment.domain.po.PunishmentBillType
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal

data class PunishmentBillSaveDto(
    val name: String,
    val longitude: String,
    val latitude: String,
    val time: String,
    @ApiModelProperty(value = "类型")
    val type: PunishmentBillType,
    @ApiModelProperty(value = "处理方式")
    val mode: PunishmentBillMode,
    @ApiModelProperty(value = "罚款金额", example = "0")
    val amount: BigDecimal? = null,
    @ApiModelProperty(value = "违法者姓名")
    val offenderName: String? = null,
    val offenderIdCard: String? = null,
    val offenderPhoneNumber: String? = null
)
