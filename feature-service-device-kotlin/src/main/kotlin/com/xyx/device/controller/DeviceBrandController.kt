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
import com.xyx.device.domain.repository.jpa.DeviceBrandRepository
import com.xyx.device.domain.repository.jpa.query
import com.xyx.device.domain.vo.DeviceBrandListVo
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@Api(tags = ["设备-设备品牌"])
@RestController
@RequestMapping("api/device/device_brand")
class DeviceBrandController(private val deviceBrandRepository: DeviceBrandRepository) {
    @GetMapping("v1")
    fun queryAll(dto: DeviceBrandSearchDto) = returnSuccess(deviceBrandRepository.query(dto, emptyArray(), arrayOf("name"))
        .map { DeviceBrandListVo.vo(it) })

    @PostMapping("v1")
    fun save(dto: DeviceBrandSaveDto): Return {
        if (deviceBrandRepository.existsByNameAndDeletedFalse(dto.name)) {
            return returnCode(ErrorCodeDevice.DEVICE_TYPE_001)
        }
        deviceBrandRepository.save(DeviceBrand(dto.name))
        return returnSuccess()
    }

    @PutMapping("v1")
    fun update(dto: DeviceBrandUpdateDto): Return {
        val entityOptional = deviceBrandRepository.findById(dto.uuid)
        if (entityOptional.isPresent) {
            if (deviceBrandRepository.existsByNameAndDeletedFalseAndUuidNot(dto.name, dto.uuid)) {
                return returnCode(ErrorCodeDevice.DEVICE_TYPE_001)
            }
            deviceBrandRepository.save(entityOptional.get()
                .apply { name = dto.name })
            return returnSuccess()
        }
        return returnCode(ErrorCodeCommon.COMMON_UUID_UNKNOWN)
    }

    @DeleteMapping("v1/{uuid}")
    fun delete(@PathVariable uuid: String): Return {
        deviceBrandRepository.deleted(uuid)
        return returnSuccess()
    }

    @PostMapping("v1/{uuid}/enable")
    fun enable(@PathVariable uuid: String): Return {
        deviceBrandRepository.enable(uuid)
        return returnSuccess()
    }
}