package com.xyx.security

import com.xyx.common.func.JacksonFun
import com.xyx.common.func.returnCode
import com.xyx.security.constant.ErrorCodeSecurity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_FORBIDDEN

object AccessDeniedHandlerS : AccessDeniedHandler {
    override fun handle(request: HttpServletRequest, response: HttpServletResponse, accessDeniedException: AccessDeniedException) {
        response.apply {
            setHeader("content-type", "application/json;charset=UTF-8")
            status = SC_FORBIDDEN
            writer.print(JacksonFun.toJson(returnCode(ErrorCodeSecurity.SECURITY_005)))
        }
    }
}