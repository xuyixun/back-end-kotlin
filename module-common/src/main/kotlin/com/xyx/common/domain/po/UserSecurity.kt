package com.xyx.common.domain.po

import org.hibernate.annotations.ColumnDefault
import javax.persistence.ElementCollection
import javax.persistence.Entity

@Entity
data class UserSecurity(
    @ColumnDefault("0") var disabled: Boolean,
    val username: String,
    val password: String,
    val phone: String,
    val role: String,
    @ElementCollection var roles: Set<String>,
    var wxOpenid: String
) : CommonPo()
