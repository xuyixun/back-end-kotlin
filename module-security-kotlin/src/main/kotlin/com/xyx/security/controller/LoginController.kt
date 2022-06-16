package com.xyx.security.controller

import com.xyx.common.domain.repository.UserSecurityRepository
import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnSuccess
import com.xyx.security.constant.ErrorCodeSecurity
import com.xyx.security.controller.dto.LoginUsernamePasswordDto
import com.xyx.security.func.JwtFun
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/login")
class LoginController(
    private val jwtFun: JwtFun, private val userSecurityRepository: UserSecurityRepository, private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    @PostMapping("v1/up")
    fun login(@RequestBody dto: LoginUsernamePasswordDto): Return {
        if (dto.check()) {
            return returnSuccess(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val userSecurityOptional = this.userSecurityRepository.findByUsername(dto.username)
        if (userSecurityOptional.isPresent) {
            val u = userSecurityOptional.get()
            if (u.disabled) {
                return returnSuccess(ErrorCodeSecurity.SECURITY_007)
            }
            if (bCryptPasswordEncoder.matches(dto.password, u.password)) {
                return returnSuccess(jwtFun.buildUserSecurity(u))
            }
        }
        return returnSuccess(ErrorCodeSecurity.SECURITY_006)
    }
}