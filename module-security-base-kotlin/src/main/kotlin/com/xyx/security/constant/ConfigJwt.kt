package com.xyx.security.constant

import org.springframework.stereotype.Component
import org.yaml.snakeyaml.Yaml

@Component
object ConfigJwt {
    val tokenHeader: String
    val tokenPrefix: String
    val secret: String
    val expirationSecond: Int
    const val tokenVersion1 = 1

    init {
        val jwt = Yaml().load<Map<String, Any>>(
            this.javaClass
                .classLoader
                .getResourceAsStream("config-jwt.yml")
        )
        tokenHeader = if (jwt["token_header"]!! is String) jwt["token_header"] as String else ""
        tokenPrefix = if (jwt["token_prefix"]!! is String) jwt["token_prefix"] as String else ""
        secret = if (jwt["secret"]!! is String) jwt["secret"] as String else ""
        expirationSecond = if (jwt["expiration_second"]!! is Int) jwt["expiration_second"] as Int else 0
    }
}