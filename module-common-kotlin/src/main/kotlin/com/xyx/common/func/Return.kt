package com.xyx.common.func

import io.swagger.annotations.ApiModelProperty

data class Return(
    @ApiModelProperty(value = "返回标识(\"0\"成功)") val code: String,
    @ApiModelProperty(value = "错误消息") val errMsg: String,
    @ApiModelProperty(value = "错误消息(中文)") val errMsgZh: String,
    @ApiModelProperty(value = "数据") val data: Any?
)

fun returnSuccess() = Return("0", "success", "调用成功", null)
fun returnSuccess(data: Any) = Return("0", "", "", data)
fun returnCode(errorCode: ErrorCode) = Return(errorCode.toString(), errorCode.errorMsg(), errorCode.errorMsgZH(), null)
interface ErrorCode {
    fun errorMsg(): String
    fun errorMsgZH(): String
}

enum class ErrorCodeCommon(private val errorMsg: String, private val errorMsgZH: String) : ErrorCode {
    COMMON_UNKNOWN("unknown error", "未知错误"),
    COMMON_UUID_UNKNOWN("uuid unknown", "数据不存在"),
    COMMON_PARAMS_ERROR("params error", "参数错误");

    override fun errorMsg(): String = errorMsg
    override fun errorMsgZH(): String = errorMsgZH
}
