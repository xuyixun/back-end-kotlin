package com.xyx.structure.controller

import com.xyx.common.domain.repository.deleted
import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.file.domain.po.CommonFile
import com.xyx.structure.domain.dto.StructureFloorSaveDto
import com.xyx.structure.domain.dto.StructureFloorUpdateDto
import com.xyx.structure.domain.po.Structure
import com.xyx.structure.domain.po.StructureFloor
import com.xyx.structure.domain.repository.StructureFloorRepository
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@Api(tags = ["建筑区域-建筑区域楼层"])
@RestController
@RequestMapping("api/structure/structure_floor")
class StructureFloorController(private val structureFloorRepository: StructureFloorRepository) {
    @PostMapping("v1")
    fun save(dto: StructureFloorSaveDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        structureFloorRepository.save(StructureFloor(Structure.create(dto.structureUuid), dto.number, CommonFile.create(dto.mapImageUuid)))
        return returnSuccess()
    }

    @PutMapping("v1")
    fun update(dto: StructureFloorUpdateDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val entityOptional = structureFloorRepository.findById(dto.uuid)
        if (entityOptional.isPresent) {
            structureFloorRepository.save(
                entityOptional.get()
                    .apply {
                        number = dto.number
                        mapImage = CommonFile.create(dto.mapImageUuid)
                    })
            return returnSuccess()
        }
        return returnCode(ErrorCodeCommon.COMMON_UUID_UNKNOWN)
    }

    @DeleteMapping("v1/{uuid}")
    fun delete(@PathVariable uuid: String): Return {
        structureFloorRepository.deleted(uuid)
        return returnSuccess()
    }
}