package com.xyx.common.domain.po

import java.time.LocalDateTime

interface CommonPoDeleted {
    var deleted: Boolean
    var deletedTime: LocalDateTime?
}
