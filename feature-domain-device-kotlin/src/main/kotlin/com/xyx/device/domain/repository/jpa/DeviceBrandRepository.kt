package com.xyx.device.domain.repository.jpa

import com.xyx.common.domain.repository.CommonRepositoryDelete
import com.xyx.common.domain.repository.CommonRepositoryEnable
import com.xyx.device.domain.dto.DeviceBrandSearchDto
import com.xyx.device.domain.po.DeviceBrand
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface DeviceBrandRepository : JpaRepository<DeviceBrand, String>, JpaSpecificationExecutor<DeviceBrand>, CommonRepositoryDelete<DeviceBrand, String>, CommonRepositoryEnable<DeviceBrand, String> {
    fun existsByNameAndDeletedFalse(name: String): Boolean

    fun existsByNameAndDeletedFalseAndUuidNot(name: String, uuid: String): Boolean
}

fun DeviceBrandRepository.query(dto: DeviceBrandSearchDto, d: Array<String>, a: Array<String>): List<DeviceBrand> = this.findAll(
    { root, _, cb ->
        val predicate = cb.conjunction()
        val expressions = predicate.expressions
        if (!com.google.common.base.Strings.isNullOrEmpty(dto.name)) {
            expressions.add(cb.like(root.get("name"), "%" + (dto.name) + "%"))
        }
        if (!dto.showDisabled) {
            expressions.add(cb.isTrue(root.get("enabled")))
        }
        expressions.add(cb.isFalse(root.get("deleted")))
        predicate
    },
    Sort.by(d.map { Sort.Order.desc(it) }
        .plus(a.map { Sort.Order.asc(it) }))
)