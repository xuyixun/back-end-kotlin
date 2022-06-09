package com.xyx.common.domain.po

import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class CommonPoDeleted(
    var deleted: Boolean = false,
    var deletedTime: LocalDateTime? = null
) : CommonPo()
