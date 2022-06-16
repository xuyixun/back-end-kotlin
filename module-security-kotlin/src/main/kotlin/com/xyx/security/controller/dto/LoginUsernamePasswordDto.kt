package com.xyx.security.controller.dto

import com.google.common.base.Strings

data class LoginUsernamePasswordDto(
    val username: String, val password: String
) {
    fun check() = Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)
}
