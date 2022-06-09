package com.xyx.device.controller

import com.xyx.common.domain.repository.deleted
import com.xyx.common.domain.repository.enable
import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.device.constant.ErrorCodeDevice
import com.xyx.device.domain.dto.DeviceBrandSaveDto
import com.xyx.device.domain.dto.DeviceBrandSearchDto
import com.xyx.device.domain.dto.DeviceBrandUpdateDto
import com.xyx.device.domain.po.DeviceBrand
import com.xyx.device.domain.repository.DeviceBrandRepository
import com.xyx.device.domain.repository.query
import com.xyx.device.domain.vo.DeviceBrandListVo
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@Api(tags = ["设备-设备品牌"])
@RestController
@RequestMapping("api/device/device_brand")
class DeviceBrandController(private val DeviceBrandRepository: DeviceBrandRepository) {
    @GetMapping("v1")
    fun queryAll(dto: DeviceBrandSearchDto) = returnSuccess(DeviceBrandRepository.query(dto, emptyArray(), arrayOf("name"))
        .map { DeviceBrandListVo.vo(it) })

    @PostMapping("v1")
    fun save(dto: DeviceBrandSaveDto): Return {
        if (DeviceBrandRepository.existsByNameAndDeletedFalse(dto.name)) {
            return returnCode(ErrorCodeDevice.DEVICE_TYPE_001)
        }
        DeviceBrandRepository.save(DeviceBrand(dto.name))
        return returnSuccess()
    }

    @PutMapping("v1")
    fun update(dto: DeviceBrandUpdateDto): Return {
        val entityOptional = DeviceBrandRepository.findById(dto.uuid)
        if (entityOptional.isPresent) {
            if (DeviceBrandRepository.existsByNameAndDeletedFalseAndUuidNot(dto.name, dto.uuid)) {
                return returnCode(ErrorCodeDevice.DEVICE_TYPE_001)
            }
            DeviceBrandRepository.save(entityOptional.get()
                .apply { name = dto.name })
            return returnSuccess()
        }
        return returnCode(ErrorCodeCommon.COMMON_UUID_UNKNOWN)
    }

    @DeleteMapping("v1/{uuid}")
    fun delete(@PathVariable uuid: String): Return {
        DeviceBrandRepository.deleted(uuid)
        return returnSuccess()
    }

    @PostMapping("v1/{uuid}/enable")
    fun enable(@PathVariable uuid: String): Return {
        DeviceBrandRepository.enable(uuid)
        return returnSuccess()
    }
}