package com.xyx.device.domain.repository.jpa

import com.xyx.common.domain.repository.CommonRepositoryEnable
import com.xyx.device.domain.dto.DeviceSearchDto
import com.xyx.device.domain.po.Device
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface DeviceRepository : JpaRepository<Device, String>, JpaSpecificationExecutor<Device>, CommonRepositoryEnable<Device, String> {
    fun existsByUidAndDeletedFalse(name: String): Boolean

    fun existsByUidAndDeletedFalseAndUuidNot(name: String, uuid: String): Boolean
}

fun DeviceRepository.query(dto: DeviceSearchDto, d: Array<String>, a: Array<String>): Page<Device> = this.findAll(
    { root, _, cb ->
        val predicate = cb.conjunction()
        val expressions = predicate.expressions
        if (!com.google.common.base.Strings.isNullOrEmpty(dto.uid)) {
            expressions.add(cb.like(root.get("uid"), "%" + (dto.uid) + "%"))
        }
        expressions.add(cb.isFalse(root.get("deleted")))
        predicate
    }, PageRequest.of(
        dto.page,
        dto.size,
        Sort.by(d.map { Sort.Order.desc(it) }
            .plus(a.map { Sort.Order.asc(it) }))
    )
)