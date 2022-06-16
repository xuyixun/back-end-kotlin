package com.xyx.security.controller

import com.xyx.common.domain.po.UserSecurity
import com.xyx.common.domain.repository.UserSecurityRepository
import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnSuccess
import com.xyx.security.controller.dto.UserSecuritySave
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/user_security")
class UserSecurityController(private val userSecurityRepository: UserSecurityRepository, private val bCryptPasswordEncoder: BCryptPasswordEncoder) {
    @PostMapping("v1/save")
    fun login(@RequestBody dto: UserSecuritySave): Return {
        if (dto.check()) {
            return returnSuccess(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        this.userSecurityRepository.save(UserSecurity(dto.username, bCryptPasswordEncoder.encode(dto.password), "", dto.role))
        return returnSuccess()
    }
}