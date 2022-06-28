package com.xyx.device.domain.vo

import com.xyx.device.domain.po.Device
import com.xyx.structure.domain.repository.StructureFloorDeviceRelationRepository

data class DeviceListVo(
    val uuid: String,
    val enable: Boolean,
    val uid: String,
    val brandName: String,
    val typeName: String,
    val model: String,
    val floorNumber: Byte = 0,
    val structureName: String? = null
) {
    companion object {
        fun vo(entity: Device) = DeviceListVo(entity.uuid, entity.enabled, entity.uid, entity.brand.name, entity.type.name, entity.model)

        fun vo(entity: Device, structureFloorDeviceRelationRepository: StructureFloorDeviceRelationRepository) = structureFloorDeviceRelationRepository.findByDeviceUuid(entity.uuid)
            .run {
                if (this.isPresent) {
                    DeviceListVo(entity.uuid, entity.enabled, entity.uid, entity.brand.name, entity.type.name, entity.model, get().structureFloor.number, get().structureFloor.structure.name)
                } else {
                    vo(entity)
                }
            }
    }
}
