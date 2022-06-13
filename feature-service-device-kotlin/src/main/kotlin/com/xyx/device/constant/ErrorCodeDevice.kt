package com.xyx.device.constant

import com.xyx.common.func.ErrorCode

enum class ErrorCodeDevice(private val errorMsg: String, private val errorMsgZH: String) : ErrorCode {
    DEVICE_TYPE_001("name exists", "名称已存在"),
    DEVICE_001("name exists", "设备uid存在"), ;

    override fun errorMsg(): String = errorMsg
    override fun errorMsgZH(): String = errorMsgZH
}