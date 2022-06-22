package com.xyx.security.controller

import com.xyx.common.domain.repository.UserSecurityRepository
import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnSuccess
import com.xyx.security.constant.ErrorCodeSecurity
import com.xyx.security.controller.dto.LoginUsernamePasswordDto
import com.xyx.security.func.JwtFunc
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["登录授权"])
@RestController
@RequestMapping("api/login")
class LoginController(
    private val jwtFunc: JwtFunc, private val userSecurityRepository: UserSecurityRepository, private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    @ApiOperation(value = "账号密码登录")
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
                return returnSuccess(jwtFunc.buildUserSecurity(u))
            }
        }
        return returnSuccess(ErrorCodeSecurity.SECURITY_006)
    }
}