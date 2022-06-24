package com.xyx.device.controller

import com.xyx.common.domain.repository.deleted
import com.xyx.common.domain.repository.enable
import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.device.constant.ErrorCodeDevice
import com.xyx.device.domain.dto.DeviceTypeUpdateDto
import com.xyx.device.domain.dto.save.DeviceTypeSaveDto
import com.xyx.device.domain.dto.search.DeviceTypeSearchDto
import com.xyx.device.domain.po.DeviceType
import com.xyx.device.domain.repository.DeviceTypeRepository
import com.xyx.device.domain.repository.query
import com.xyx.device.domain.vo.DeviceTypeListVo
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@Api(tags = ["设备-设备类型"])
@RestController
@RequestMapping("api/device/device_type")
class DeviceTypeController(private val deviceTypeRepository: DeviceTypeRepository) {
    @GetMapping("v1")
    fun queryAll(@RequestBody dto: DeviceTypeSearchDto) = returnSuccess(deviceTypeRepository.query(dto, emptyArray(), arrayOf("name"))
        .map { DeviceTypeListVo.vo(it) })

    @PostMapping("v1")
    fun save(@RequestBody dto: DeviceTypeSaveDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        if (deviceTypeRepository.existsByNameAndDeletedFalse(dto.name)) {
            return returnCode(ErrorCodeDevice.DEVICE_TYPE_001)
        }
        deviceTypeRepository.save(DeviceType(dto.name))
        return returnSuccess()
    }

    @PutMapping("v1")
    fun update(@RequestBody dto: DeviceTypeUpdateDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val entityOptional = deviceTypeRepository.findById(dto.uuid)
        if (entityOptional.isPresent) {
            if (deviceTypeRepository.existsByNameAndDeletedFalseAndUuidNot(dto.name, dto.uuid)) {
                return returnCode(ErrorCodeDevice.DEVICE_TYPE_001)
            }
            deviceTypeRepository.save(entityOptional.get()
                .apply { name = dto.name })
            return returnSuccess()
        }
        return returnCode(ErrorCodeCommon.COMMON_UUID_UNKNOWN)
    }

    @DeleteMapping("v1/{uuid}")
    fun delete(@PathVariable uuid: String): Return {
        deviceTypeRepository.deleted(uuid)
        return returnSuccess()
    }

    @PostMapping("v1/{uuid}/enable")
    fun enable(@PathVariable uuid: String): Return {
        deviceTypeRepository.enable(uuid)
        return returnSuccess()
    }
}