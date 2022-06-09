package com.xyx.device.domain.repository

import com.xyx.common.domain.repository.CommonRepositoryDelete
import com.xyx.device.domain.po.Device
import org.springframework.data.jpa.repository.JpaRepository

interface DeviceRepository : JpaRepository<Device, String>, CommonRepositoryDelete<Device, String>