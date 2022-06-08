package com.xyx.test

import com.xyx.common.domain.po.UserSecurity
import com.xyx.common.domain.service.UserSecurityService
import com.xyx.security.func.CurrentUser
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
open class TestApp(private val server: UserSecurityService) {
    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("test")
    open fun testRequest() {
        val x = CurrentUser.user()
        println(x?.username)
        server.jpaSave(UserSecurity("123", "435", "645", "435"))
    }
}