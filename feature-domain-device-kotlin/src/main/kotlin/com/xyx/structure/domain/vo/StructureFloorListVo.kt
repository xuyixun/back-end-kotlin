package com.xyx.structure.domain.vo

import com.xyx.file.domain.vo.CommonFileSimpleVo
import com.xyx.structure.domain.po.StructureFloor

data class StructureFloorListVo(val uuid: String, val enable: Boolean, val number: Byte, val mapImage: CommonFileSimpleVo?) {
    companion object {
        fun vo(entity: StructureFloor) = StructureFloorListVo(entity.uuid, entity.enabled, entity.number, entity.mapImage?.let { CommonFileSimpleVo.vo(it) })
    }
}