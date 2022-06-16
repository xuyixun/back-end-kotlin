package com.xyx.security.constant

import com.xyx.common.func.ErrorCode

enum class ErrorCodeSecurity(private val errorMsg: String, private val errorMsgZH: String) : ErrorCode {
    SECURITY_001("token empty", "无认证信息"),
    SECURITY_002("token unknown", "token无效"),
    SECURITY_003("token expired", "token过期"),
    SECURITY_004("token invalid", "token失效"),
    SECURITY_005("Insufficient permissions", "权限不足"),
    SECURITY_006("Incorrect username or password", "账号或密码错误"),
    SECURITY_007("User is disabled", "用户已禁用"), ;

    override fun errorMsg(): String = errorMsg
    override fun errorMsgZH(): String = errorMsgZH
}