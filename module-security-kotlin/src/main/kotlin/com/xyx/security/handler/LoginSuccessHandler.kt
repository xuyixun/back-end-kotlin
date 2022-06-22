package com.xyx.security.handler

import com.xyx.common.domain.po.UserSecurity
import com.xyx.common.func.JacksonFunc
import com.xyx.common.func.returnSuccess
import com.xyx.security.func.JwtFunc
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginSuccessHandler(private val jwtFunc: JwtFunc) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        if (authentication.principal != null) {
            val principal = authentication.principal
            if (principal is User) {
                val token =
                    jwtFunc.buildUserSecurity(
                        UserSecurity(
                            "", "", "", "", principal.authorities
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toSet())
                        ).apply { uuid(principal.username) }
                    )
                response.setHeader("content-type", "application/json;charset=UTF-8")
                response.writer
                    .print(JacksonFunc.toJson(returnSuccess(token)))
            }
        }
    }
}