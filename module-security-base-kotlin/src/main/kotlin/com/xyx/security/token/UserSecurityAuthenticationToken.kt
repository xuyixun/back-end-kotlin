package com.xyx.security.token

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UserSecurityAuthenticationToken(private val principal: Any, val role: String, authorities: Collection<GrantedAuthority>) : AbstractAuthenticationToken(authorities) {
    init {
        super.setAuthenticated(true)
    }

    override fun getCredentials(): Any = ""
    override fun getPrincipal(): Any = principal
}