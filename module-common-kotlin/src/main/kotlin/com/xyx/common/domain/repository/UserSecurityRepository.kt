package com.xyx.common.domain.repository

import com.xyx.common.domain.po.UserSecurity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserSecurityRepository : JpaRepository<UserSecurity, String> {
    fun findByUsername(username: String): Optional<UserSecurity>
}