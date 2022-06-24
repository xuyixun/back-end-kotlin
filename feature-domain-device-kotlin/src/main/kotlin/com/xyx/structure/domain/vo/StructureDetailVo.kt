package com.xyx.structure.domain.vo

import com.xyx.structure.domain.po.Structure
import com.xyx.structure.domain.po.StructureFloor

data class StructureDetailVo(val uuid: String, val name: String, val enable: Boolean, val structureFloorSet: List<StructureFloorListVo>) {
    companion object {
        fun vo(entity: Structure, structureFloorList: List<StructureFloor>) = StructureDetailVo(entity.uuid, entity.name, entity.enabled, structureFloorList.map { StructureFloorListVo.vo(it) })
    }
}