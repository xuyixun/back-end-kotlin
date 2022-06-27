package com.xyx.device.domain.repository

import com.xyx.common.domain.repository.CommonRepositoryDelete
import com.xyx.common.domain.repository.CommonRepositoryEnable
import com.xyx.device.domain.dto.search.DeviceTypeSearchDto
import com.xyx.device.domain.po.DeviceType
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface DeviceTypeRepository : JpaRepository<DeviceType, String>, JpaSpecificationExecutor<DeviceType>, CommonRepositoryDelete<DeviceType, String>, CommonRepositoryEnable<DeviceType, String> {
    fun existsByNameAndDeletedFalse(name: String): Boolean

    fun existsByNameAndDeletedFalseAndUuidNot(name: String, uuid: String): Boolean
}

fun DeviceTypeRepository.query(dto: DeviceTypeSearchDto, d: Array<String>, a: Array<String>): List<DeviceType> = this.findAll(
    { root, _, cb ->
        val predicate = cb.conjunction()
        val expressions = predicate.expressions
        if (!com.google.common.base.Strings.isNullOrEmpty(dto.name)) {
            expressions.add(cb.like(root.get("name"), "%" + (dto.name) + "%"))
        }
        if (dto.hideDisabled) {
            expressions.add(cb.isTrue(root.get("enabled")))
        }
        expressions.add(cb.isFalse(root.get("deleted")))
        predicate
    },
    Sort.by(d.map { Sort.Order.desc(it) }
        .plus(a.map { Sort.Order.asc(it) }))
)
