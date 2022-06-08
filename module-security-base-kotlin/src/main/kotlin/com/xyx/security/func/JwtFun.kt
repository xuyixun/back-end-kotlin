package com.xyx.security.func

import com.xyx.common.domain.po.UserSecurity
import com.xyx.security.constant.ConfigJwt
import com.xyx.security.exception.JwtExpiredException
import com.xyx.security.exception.JwtParseFailException
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import java.security.Key
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
open class JwtFun {
    private fun generateKey(): Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(ConfigJwt.secret))
    fun decode(token: String): Jws<Claims> {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is UnsupportedJwtException, is MalformedJwtException, is SignatureException, is IllegalArgumentException -> throw JwtParseFailException("解析token失败")
                is ExpiredJwtException -> throw JwtExpiredException("token过期")
                else -> throw Exception("其它错误")
            }
        }
    }

    @Cacheable(value = ["token_jwt"], key = "#subject")
    open fun cacheTokenUserUuid(subject: String): String = subject

    private fun build(subject: String, role: String, roles: Set<String>): JwtBuilder = Jwts.builder()
        .setId(
            UUID.randomUUID()
                .toString()
        )
        .apply {
            val now = LocalDateTime.now()
            setIssuedAt(
                Date.from(
                    now.atZone(ZoneId.systemDefault())
                        .toInstant()
                )
            )
            setExpiration(
                Date.from(
                    now.plusSeconds(ConfigJwt.expirationSecond.toLong())
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                )
            )
        }
        .signWith(generateKey())
        .claim("version", ConfigJwt.tokenVersion1)
        .claim("role", role)
        .claim("roles", roles)
        .setSubject(subject)

    @CachePut(value = ["token_jwt"], key = "#userSecurity.uuid")
    open fun buildUserSecurity(userSecurity: UserSecurity): String = ConfigJwt.tokenPrefix + build(userSecurity.uuid, userSecurity.role, userSecurity.roles).compact()
}