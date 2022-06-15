package com.xyx.punishment.constant

import com.xyx.common.func.ErrorCode

enum class ErrorCodePunishment(private val errorMsg: String, private val errorMsgZH: String) : ErrorCode {
    UPLOAD_001("update error", "上传失败");

    override fun errorMsg(): String = errorMsg

    override fun errorMsgZH(): String = errorMsgZH

}