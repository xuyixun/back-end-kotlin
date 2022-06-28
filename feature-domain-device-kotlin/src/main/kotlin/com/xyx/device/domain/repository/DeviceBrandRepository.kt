package com.xyx.device.domain.repository

import com.google.common.base.Strings
import com.xyx.common.domain.repository.CommonRepositoryDelete
import com.xyx.common.domain.repository.CommonRepositoryEnable
import com.xyx.device.domain.dto.search.DeviceBrandSearchDto
import com.xyx.device.domain.po.DeviceBrand
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface DeviceBrandRepository : JpaRepository<DeviceBrand, String>, JpaSpecificationExecutor<DeviceBrand>, CommonRepositoryDelete<DeviceBrand, String>, CommonRepositoryEnable<DeviceBrand, String> {
    fun existsByNameAndDeletedFalse(name: String): Boolean

    fun existsByNameAndDeletedFalseAndUuidNot(name: String, uuid: String): Boolean
}

fun DeviceBrandRepository.query(dto: DeviceBrandSearchDto, d: Array<String>, a: Array<String>): List<DeviceBrand> = this.findAll(
    { root, _, cb ->
        val predicate = cb.conjunction()
        val expressions = predicate.expressions
        if (!Strings.isNullOrEmpty(dto.name)) {
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