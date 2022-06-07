package com.xyx.common.domain.service

import com.xyx.common.domain.po.UserSecurity
import com.xyx.common.domain.repository.UserSecurityRepository
import org.springframework.stereotype.Service

@Service
class UserSecurityService(private val repository: UserSecurityRepository) {
    fun jpaSave(s: UserSecurity) {
        repository.save(s)
    }
}