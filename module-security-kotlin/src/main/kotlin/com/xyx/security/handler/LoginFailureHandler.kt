package com.xyx.security.handler

import com.xyx.common.func.JacksonFun
import com.xyx.common.func.returnCode
import com.xyx.security.constant.ErrorCodeSecurity
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object LoginFailureHandler : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse?, exception: AuthenticationException?) {
        response?.apply {
            setHeader("content-type", "application/json;charset=UTF-8")
            writer.print(JacksonFun.toJson(returnCode(ErrorCodeSecurity.SECURITY_006)))
        }
    }
}