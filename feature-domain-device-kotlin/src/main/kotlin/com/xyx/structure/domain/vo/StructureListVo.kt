package com.xyx.structure.domain.vo

import com.xyx.structure.domain.po.Structure

data class StructureListVo(val uuid: String, val enable: Boolean, val name: String, val longitude: String, val latitude: String) {
    companion object {
        fun vo(entity: Structure) = StructureListVo(entity.uuid, entity.enabled, entity.name, entity.longitude, entity.latitude)
    }
}