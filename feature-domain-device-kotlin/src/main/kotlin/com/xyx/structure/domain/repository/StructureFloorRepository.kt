package com.xyx.structure.domain.repository

import com.xyx.common.domain.repository.CommonRepositoryDelete
import com.xyx.structure.domain.dto.search.StructureFloorSearchDto
import com.xyx.structure.domain.po.Structure
import com.xyx.structure.domain.po.StructureFloor
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface StructureFloorRepository : JpaRepository<StructureFloor, String>, JpaSpecificationExecutor<StructureFloor>, CommonRepositoryDelete<StructureFloor, String> {
    fun existsByNumber(number: Byte): Boolean

    fun existsByNumberAndUuidNot(number: Byte, uuid: String): Boolean
}

fun StructureFloorRepository.query(dto: StructureFloorSearchDto, d: Array<String>, a: Array<String>): List<StructureFloor> = this.findAll(
    { root, _, cb ->
        val predicate = cb.conjunction()
        val expressions = predicate.expressions
        expressions.add(
            cb.equal(
                root.get<Structure>("structure")
                    .get<String>("uuid"), dto.structureUuid
            )
        )
        if (dto.hideDisabled) {
            expressions.add(cb.isTrue(root.get("enabled")))
        }
        expressions.add(cb.isFalse(root.get("deleted")))
        predicate
    },
    Sort.by(d.map { Sort.Order.desc(it) }
        .plus(a.map { Sort.Order.asc(it) }))
)