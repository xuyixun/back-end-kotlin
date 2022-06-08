package com.xyx.security.exception

import org.springframework.security.core.AuthenticationException

class TokenMatchCacheAuthException(msg: String) : AuthenticationException(msg)