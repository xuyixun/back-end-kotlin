package com.xyx.structure.controller

import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.device.domain.po.Device
import com.xyx.structure.constant.ErrorCodeStructure
import com.xyx.structure.domain.dto.save.StructureFloorDeviceRelationSaveDto
import com.xyx.structure.domain.dto.search.StructureFloorDeviceRelationSearchDto
import com.xyx.structure.domain.po.StructureFloor
import com.xyx.structure.domain.po.StructureFloorDeviceRelation
import com.xyx.structure.domain.repository.StructureFloorDeviceRelationRepository
import com.xyx.structure.domain.repository.query
import com.xyx.structure.domain.vo.StructureFloorDeviceRelationListVo
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@Api(tags = ["建筑区域-楼层设备"])
@RestController
@RequestMapping("api/structure/structure_floor_device")
class StructureFloorDeviceRelationController(private val structureFloorDeviceRelationRepository: StructureFloorDeviceRelationRepository) {
    @GetMapping("v1")
    fun queryAll(dto: StructureFloorDeviceRelationSearchDto) = returnSuccess(structureFloorDeviceRelationRepository.query(dto, emptyArray(), emptyArray())
        .map { StructureFloorDeviceRelationListVo.vo(it) })

    @PostMapping("v1")
    fun save(@RequestBody dto: StructureFloorDeviceRelationSaveDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        if (structureFloorDeviceRelationRepository.existsByStructureFloorUuidAndDeviceUuid(dto.structureFloorUuid, dto.deviceUuid)) {
            return returnCode(ErrorCodeStructure.STRUCTURE_FLOOR_DEVICE_001)
        }
        structureFloorDeviceRelationRepository.save(
            StructureFloorDeviceRelation(
                StructureFloor.create(dto.structureFloorUuid), Device.create(dto.deviceUuid), dto.pointX, dto.pointY
            )
        )
        return returnSuccess()
    }
}