package com.xyx.file.domain.vo

import com.xyx.file.domain.po.CommonFile
import com.xyx.file.domain.po.CommonFileType

data class CommonFileSimpleVo(val uuid: String, val path: String, val type: CommonFileType) {
    companion object {
        fun vo(entity: CommonFile) = CommonFileSimpleVo(entity.uuid, entity.path, entity.type)
    }
}
