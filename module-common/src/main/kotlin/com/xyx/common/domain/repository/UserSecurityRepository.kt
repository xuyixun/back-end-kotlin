package com.xyx.common.domain.repository

import com.xyx.common.domain.po.UserSecurity
import org.springframework.data.jpa.repository.JpaRepository

interface UserSecurityRepository : JpaRepository<UserSecurity, String>