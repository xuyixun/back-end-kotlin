package com.xyx.file.domain.vo

import com.xyx.file.domain.po.CommonFile

data class CommonFilePathVo(val path: String) {
    companion object {
        fun vo(entity: CommonFile) = CommonFilePathVo(entity.path)
    }
}
