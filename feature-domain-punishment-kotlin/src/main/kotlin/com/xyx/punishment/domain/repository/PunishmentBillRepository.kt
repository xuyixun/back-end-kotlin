package com.xyx.punishment.domain.repository

import com.xyx.punishment.domain.dto.PunishmentBillSearchDto
import com.xyx.punishment.domain.po.PunishmentBill
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface PunishmentBillRepository : JpaRepository<PunishmentBill, String>, JpaSpecificationExecutor<PunishmentBill>

fun PunishmentBillRepository.query(dto: PunishmentBillSearchDto, d: Array<String>, a: Array<String>): Page<PunishmentBill> = this.findAll(
    { root, _, cb ->
        val predicate = cb.conjunction()
        val expressions = predicate.expressions
        if (!com.google.common.base.Strings.isNullOrEmpty(dto.name)) {
            expressions.add(cb.like(root.get("name"), "%" + (dto.name) + "%"))
        }
        predicate
    }, org.springframework.data.domain.PageRequest.of(
        dto.page,
        dto.size,
        org.springframework.data.domain.Sort.by(d.map { org.springframework.data.domain.Sort.Order.desc(it) }
            .plus(a.map { org.springframework.data.domain.Sort.Order.asc(it) }))
    )
)