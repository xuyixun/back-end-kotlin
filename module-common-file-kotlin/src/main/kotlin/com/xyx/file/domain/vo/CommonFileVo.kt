package com.xyx.file.domain.vo

import com.xyx.file.domain.po.CommonFile

data class CommonFileVo(val uuid: String, val path: String) {
    companion object {
        fun vo(entity: CommonFile) = CommonFileVo(entity.uuid, entity.path)
    }
}
