package com.xyx.security.controller.dto

import com.google.common.base.Strings

data class UserSecuritySave(val username: String, val password: String, val role: String) {
    fun check() = Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password) || Strings.isNullOrEmpty(role)
}

