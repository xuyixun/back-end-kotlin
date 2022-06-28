package com.xyx.structure.domain.repository

import com.google.common.base.Strings
import com.xyx.common.domain.repository.CommonRepositoryDelete
import com.xyx.common.domain.repository.CommonRepositoryEnable
import com.xyx.structure.domain.dto.search.StructureSearchDto
import com.xyx.structure.domain.po.Structure
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface StructureRepository : JpaRepository<Structure, String>, JpaSpecificationExecutor<Structure>, CommonRepositoryDelete<Structure, String>, CommonRepositoryEnable<Structure, String>

fun StructureRepository.query(dto: StructureSearchDto, d: Array<String>, a: Array<String>): Page<Structure> = this.findAll(
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
    }, PageRequest.of(
        dto.page,
        dto.size,
        Sort.by(d.map { Sort.Order.desc(it) }
            .plus(a.map { Sort.Order.asc(it) }))
    )
)