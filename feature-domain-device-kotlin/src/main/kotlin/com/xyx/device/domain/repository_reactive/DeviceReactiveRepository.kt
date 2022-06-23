package com.xyx.device.domain.repository_reactive

import com.xyx.device.domain.po.Device
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface DeviceReactiveRepository : R2dbcRepository<Device, String>