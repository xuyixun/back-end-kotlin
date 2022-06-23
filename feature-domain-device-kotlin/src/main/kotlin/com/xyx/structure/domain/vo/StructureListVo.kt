package com.xyx.structure.domain.vo

import com.xyx.structure.domain.po.Structure

data class StructureListVo(val uuid: String, val name: String, val enable: Boolean) {
    companion object {
        fun vo(entity: Structure) = StructureListVo(entity.uuid, entity.name, entity.enabled)
    }
}