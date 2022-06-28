package com.xyx.structure.domain.vo

import com.xyx.device.domain.vo.DeviceDetailVo
import com.xyx.structure.domain.po.StructureFloorDeviceRelation

data class StructureFloorDeviceRelationListVo(val uuid: String, val pointX: Short, val pointY: Short, val device: DeviceDetailVo) {
    companion object {
        fun vo(entity: StructureFloorDeviceRelation) = StructureFloorDeviceRelationListVo(entity.uuid, entity.pointX, entity.pointY, DeviceDetailVo.vo(entity.device))
    }
}
