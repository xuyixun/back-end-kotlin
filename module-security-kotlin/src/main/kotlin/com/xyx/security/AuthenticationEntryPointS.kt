package com.xyx.security

import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.JacksonFunc
import com.xyx.common.func.returnCode
import com.xyx.security.constant.ErrorCodeSecurity
import com.xyx.security.exception.JwtExpiredException
import com.xyx.security.exception.JwtParseFailException
import com.xyx.security.exception.TokenMatchCacheAuthException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_OK
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

object AuthenticationEntryPointS : AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException) {
        if (response.status == SC_OK) {
            response.setHeader("content-type", "application/json;charset=UTF-8")
            response.status = SC_UNAUTHORIZED
            when (authException) {
                is InsufficientAuthenticationException -> response.writer.print(JacksonFunc.toJson(returnCode(ErrorCodeSecurity.SECURITY_001)))
                is JwtParseFailException -> response.writer.print(JacksonFunc.toJson(returnCode(ErrorCodeSecurity.SECURITY_002)))
                is JwtExpiredException -> response.writer.print(JacksonFunc.toJson(returnCode(ErrorCodeSecurity.SECURITY_003)))
                is TokenMatchCacheAuthException -> response.writer.print(JacksonFunc.toJson(returnCode(ErrorCodeSecurity.SECURITY_004)))
                else -> response.writer.print(JacksonFunc.toJson(returnCode(ErrorCodeCommon.COMMON_UNKNOWN)))
            }
        }
    }
}