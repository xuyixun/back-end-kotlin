package com.xyx.security.filter

import com.google.common.base.Strings
import com.xyx.security.constant.ConfigJwt
import com.xyx.security.exception.TokenMatchCacheAuthException
import com.xyx.security.func.JwtFun
import com.xyx.security.token.UserSecurityAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthorizationFilter(private val jwtFun: JwtFun) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val tokenHeader = request.getHeader(ConfigJwt.tokenHeader)
        if (!Strings.isNullOrEmpty(tokenHeader) && tokenHeader.startsWith(ConfigJwt.tokenPrefix)) {
            val tokenBase = tokenHeader.replace(ConfigJwt.tokenPrefix, "")
            val body = jwtFun.decode(tokenBase).body
            val subject = body.subject
            if ((ConfigJwt.tokenPrefix + tokenBase) == jwtFun.cacheTokenUserUuid(subject)) {
                if (body["version"] == ConfigJwt.tokenVersion1) {
                    val roles = body["roles"]
                    SecurityContextHolder.getContext().authentication = UserSecurityAuthenticationToken(subject,
                        body["role"].toString(),
                        if (roles is List<*>) roles.map { SimpleGrantedAuthority(it.toString()) } else emptyList())
                }
            } else {
                throw TokenMatchCacheAuthException("token失效")
            }
        }
        filterChain.doFilter(request, response)
    }
}