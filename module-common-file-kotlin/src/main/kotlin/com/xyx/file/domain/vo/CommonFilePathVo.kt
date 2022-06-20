package com.xyx.file.domain.vo

import com.xyx.file.domain.po.CommonFile
import com.xyx.file.domain.po.CommonFileType

data class CommonFilePathVo(val path: String, val type: CommonFileType) {
    companion object {
        fun vo(entity: CommonFile) = CommonFilePathVo(entity.path, entity.type)
    }
}
