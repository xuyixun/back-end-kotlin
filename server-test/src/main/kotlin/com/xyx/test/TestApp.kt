package com.xyx.test

import com.xyx.common.domain.repository.UserSecurityRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestApp(val server: UserSecurityRepository) {
    @GetMapping("test")
    fun testRequest() = "success";
}