package com.xyx.test

import com.xyx.common.domain.po.UserSecurity
import com.xyx.common.domain.service.UserSecurityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestApp(val server: UserSecurityService) {
    @GetMapping("test")
    fun testRequest() = server.jpaSave(UserSecurity("123", "435", "645", "435"));
}