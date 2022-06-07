package com.xyx.common.domain.po

import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class CommonBasePo(
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn
    var creator: UserSecurity? = null
)
