package com.xyx.structure.controller

import com.google.common.base.Strings
import com.xyx.common.domain.repository.deleted
import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.file.domain.po.CommonFile
import com.xyx.structure.constant.ErrorCodeStructure
import com.xyx.structure.domain.dto.save.StructureFloorSaveDto
import com.xyx.structure.domain.dto.search.StructureFloorSearchDto
import com.xyx.structure.domain.dto.StructureFloorUpdateDto
import com.xyx.structure.domain.po.Structure
import com.xyx.structure.domain.po.StructureFloor
import com.xyx.structure.domain.repository.StructureFloorRepository
import com.xyx.structure.domain.repository.query
import com.xyx.structure.domain.vo.StructureFloorListVo
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@Api(tags = ["建筑区域-楼层"])
@RestController
@RequestMapping("api/structure/structure_floor")
class StructureFloorController(private val structureFloorRepository: StructureFloorRepository) {
    @GetMapping("v1")
    fun queryAll(dto: StructureFloorSearchDto) = if (dto.check()) returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
    else returnSuccess(structureFloorRepository.query(dto, emptyArray(), arrayOf("number"))
        .map { StructureFloorListVo.vo(it) })

    @PostMapping("v1")
    fun save(@RequestBody dto: StructureFloorSaveDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        if (structureFloorRepository.existsByNumber(dto.number)) {
            return returnCode(ErrorCodeStructure.STRUCTURE_FLOOR_001)
        }
        structureFloorRepository.save(
            StructureFloor(
                Structure.create(dto.structureUuid), dto.number, if (Strings.isNullOrEmpty(dto.mapImageUuid)) null else CommonFile.create(dto.mapImageUuid)
            )
        )
        return returnSuccess()
    }

    @PutMapping("v1")
    fun update(@RequestBody dto: StructureFloorUpdateDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        if (structureFloorRepository.existsByNumberAndUuidNot(dto.number, dto.uuid)) {
            return returnCode(ErrorCodeStructure.STRUCTURE_FLOOR_001)
        }
        val entityOptional = structureFloorRepository.findById(dto.uuid)
        if (entityOptional.isPresent) {
            structureFloorRepository.save(entityOptional.get()
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