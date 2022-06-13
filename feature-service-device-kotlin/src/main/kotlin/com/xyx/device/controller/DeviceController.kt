package com.xyx.device.controller

import com.xyx.common.domain.repository.enable
import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.device.constant.ErrorCodeDevice
import com.xyx.device.domain.dto.DeviceSaveDto
import com.xyx.device.domain.dto.DeviceSearchDto
import com.xyx.device.domain.dto.DeviceUpdateDto
import com.xyx.device.domain.po.Device
import com.xyx.device.domain.po.DeviceBrand
import com.xyx.device.domain.po.DeviceType
import com.xyx.device.domain.repository.jpa.DeviceRepository
import com.xyx.device.domain.repository.jpa.query
import com.xyx.device.domain.vo.DeviceListVo
import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.*

@Api(tags = ["设备-设备"])
@RestController
@RequestMapping("api/device/device")
class DeviceController(private val deviceRepository: DeviceRepository) {
    @GetMapping("v1")
    fun queryAll(dto: DeviceSearchDto) =
        returnSuccess(
            deviceRepository.query(dto, emptyArray(), emptyArray())
                .map { DeviceListVo.vo(it) })

    @PostMapping("v1")
    fun save(dto: DeviceSaveDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        if (deviceRepository.existsByUidAndDeletedFalse(dto.uid)) {
            return returnCode(ErrorCodeDevice.DEVICE_001)
        }
        deviceRepository.save(Device(dto.uid, DeviceType.create(dto.deviceTypeUuid), DeviceBrand.create(dto.deviceBrandUuid)))
        return returnSuccess()
    }

    @PutMapping("v1")
    fun update(dto: DeviceUpdateDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val entityOptional = deviceRepository.findById(dto.uuid)
        if (entityOptional.isPresent) {
            if (deviceRepository.existsByUidAndDeletedFalseAndUuidNot(dto.uid, dto.uuid)) {
                return returnCode(ErrorCodeDevice.DEVICE_001)
            }
            deviceRepository.save(
                entityOptional.get()
                    .apply { uid = dto.uid })
            return returnSuccess()
        }
        return returnCode(ErrorCodeCommon.COMMON_UUID_UNKNOWN)
    }

    @PostMapping("v1/{uuid}/enable")
    fun enable(@PathVariable uuid: String): Return {
        deviceRepository.enable(uuid)
        return returnSuccess()
    }
}