package com.xyx.file.domain.vo

import com.xyx.file.domain.po.CommonFile

data class CommonFileSimpleVo(val uuid: String, val path: String) {
    companion object {
        fun vo(entity: CommonFile) = CommonFileSimpleVo(entity.uuid, entity.path)
    }
}
