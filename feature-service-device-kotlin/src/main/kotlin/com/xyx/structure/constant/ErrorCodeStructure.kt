package com.xyx.structure.constant

import com.xyx.common.func.ErrorCode

enum class ErrorCodeStructure(private val errorMsg: String, private val errorMsgZH: String) : ErrorCode {
    STRUCTURE_001("number exists", "楼层存在"), ;

    override fun errorMsg(): String = errorMsg
    override fun errorMsgZH(): String = errorMsgZH
}