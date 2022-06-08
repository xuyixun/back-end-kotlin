package com.xyx.common.domain.po

import javax.persistence.ElementCollection
import javax.persistence.Entity

@Entity
data class UserSecurity(
    val username: String,
    val password: String,
    val phone: String,
    val role: String,
    @ElementCollection val roles: Set<String> = setOf(role),
    val disabled: Boolean = false,
) : CommonPo() {
    fun uuid(uuidS: String) {
        uuid = uuidS
    }
}
