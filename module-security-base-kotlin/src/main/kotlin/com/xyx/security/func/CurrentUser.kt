package com.xyx.security.func

import com.xyx.common.domain.po.UserSecurity
import com.xyx.security.token.UserSecurityAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import java.util.stream.Collectors

object CurrentUser {
    fun user(): UserSecurity? {
        val context = SecurityContextHolder.getContext()
        val a = context.authentication
        if (a != null && "anonymousUser" != a.principal.toString()) {
            return UserSecurity(
                a.principal.toString(), "", "", if (a is UserSecurityAuthenticationToken) a.role else "", a.authorities.stream()
                    .map(
                        GrantedAuthority::getAuthority
                    )
                    .collect(Collectors.toSet())
            )
        }
        return null
    }

    fun uuid(): String {
        val context = SecurityContextHolder.getContext()
        val a = context.authentication
        if (a != null && "anonymousUser" != a.principal.toString()) {
            return a.principal.toString()
        }
        return "anonymousUser"
    }
}