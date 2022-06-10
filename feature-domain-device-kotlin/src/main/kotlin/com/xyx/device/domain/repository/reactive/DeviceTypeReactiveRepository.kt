package com.xyx.device.domain.repository.reactive

import com.xyx.device.domain.po.DeviceType
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.reactive.ReactiveSortingRepository

interface DeviceTypeReactiveRepository : QuerydslPredicateExecutor<DeviceType> {
}