package com.xyx.device.domain.repository.jpa

import com.xyx.common.domain.repository.CommonRepositoryDelete
import com.xyx.device.domain.po.Device
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceRepository : JpaRepository<Device, String>, CommonRepositoryDelete<Device, String>