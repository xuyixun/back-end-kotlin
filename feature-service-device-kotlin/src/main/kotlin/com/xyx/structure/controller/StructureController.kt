package com.xyx.structure.controller

import com.google.common.base.Strings
import com.xyx.common.domain.repository.deleted
import com.xyx.common.domain.repository.enable
import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.file.domain.po.CommonFile
import com.xyx.structure.domain.dto.StructureSaveDto
import com.xyx.structure.domain.dto.StructureSearchDto
import com.xyx.structure.domain.dto.StructureUpdateDto
import com.xyx.structure.domain.po.Structure
import com.xyx.structure.domain.po.StructureFloor
import com.xyx.structure.domain.repository.StructureFloorRepository
import com.xyx.structure.domain.repository.StructureRepository
import com.xyx.structure.domain.repository.query
import com.xyx.structure.domain.vo.StructureListVo
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@Api(tags = ["建筑区域-建筑区域"])
@RestController
@RequestMapping("api/structure/structure")
class StructureController(private val structureRepository: StructureRepository, private val structureFloorRepository: StructureFloorRepository) {
    @GetMapping("v1")
    fun queryAll(@RequestBody dto: StructureSearchDto) = returnSuccess(structureRepository.query(dto, emptyArray(), emptyArray())
        .map { StructureListVo.vo(it) })

    @PostMapping("v1")
    fun save(@RequestBody dto: StructureSaveDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        structureRepository.save(Structure(dto.name, dto.longitude, dto.latitude))
            .apply {
                structureFloorRepository.save(
                    StructureFloor(
                        this, 1, if (Strings.isNullOrEmpty(dto.mapImageUuid)) null
                        else CommonFile.create(dto.mapImageUuid)
                    )
                )
            }
        return returnSuccess()
    }

    @PutMapping("v1")
    fun update(@RequestBody dto: StructureUpdateDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val entityOptional = structureRepository.findById(dto.uuid)
        if (entityOptional.isPresent) {
            structureRepository.save(entityOptional.get()
                .apply {
                    name = dto.name
                    longitude = dto.longitude
                    latitude = dto.latitude
                })
            return returnSuccess()
        }
        return returnCode(ErrorCodeCommon.COMMON_UUID_UNKNOWN)
    }

    @DeleteMapping("v1/{uuid}")
    fun delete(@PathVariable uuid: String): Return {
        structureRepository.deleted(uuid)
        return returnSuccess()
    }

    @PostMapping("v1/{uuid}/enable")
    fun enable(@PathVariable uuid: String): Return {
        structureRepository.enable(uuid)
        return returnSuccess()
    }
}