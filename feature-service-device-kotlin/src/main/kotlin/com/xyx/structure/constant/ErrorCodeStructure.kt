package com.xyx.structure.constant

import com.xyx.common.func.ErrorCode

enum class ErrorCodeStructure(private val errorMsg: String, private val errorMsgZH: String) : ErrorCode {
    STRUCTURE_FLOOR_001("number exists", "楼层存在"),
    STRUCTURE_FLOOR_DEVICE_001("device relation exists", "设备已关联"), ;

    override fun errorMsg(): String = errorMsg
    override fun errorMsgZH(): String = errorMsgZH
}