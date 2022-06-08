package com.xyx.security.exception

import org.springframework.security.core.AuthenticationException

class JwtParseFailException(msg: String) : AuthenticationException(msg)
class JwtExpiredException(msg: String) : AuthenticationException(msg)