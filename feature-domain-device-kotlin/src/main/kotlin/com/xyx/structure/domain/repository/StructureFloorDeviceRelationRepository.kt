package com.xyx.structure.domain.repository

import com.google.common.base.Strings
import com.xyx.device.domain.po.Device
import com.xyx.device.domain.po.DeviceType
import com.xyx.structure.domain.dto.search.StructureFloorDeviceRelationSearchDto
import com.xyx.structure.domain.po.Structure
import com.xyx.structure.domain.po.StructureFloor
import com.xyx.structure.domain.po.StructureFloorDeviceRelation
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.transaction.annotation.Transactional
import java.util.*

interface StructureFloorDeviceRelationRepository : JpaRepository<StructureFloorDeviceRelation, String>, JpaSpecificationExecutor<StructureFloorDeviceRelation> {
    fun existsByDeviceUuid(deviceUuid: String): Boolean

    fun findByDeviceUuid(deviceUuid: String): Optional<StructureFloorDeviceRelation>

    @Transactional
    @Modifying
    fun deleteByDeviceUuid(deviceUuid: String)

    @Transactional
    @Modifying
    fun deleteByStructureFloorUuid(deviceUuid: String)
}

fun StructureFloorDeviceRelationRepository.query(dto: StructureFloorDeviceRelationSearchDto, d: Array<String>, a: Array<String>): Page<StructureFloorDeviceRelation> = this.findAll(
    { root, _, cb ->
        val predicate = cb.conjunction()
        val expressions = predicate.expressions
        if (!Strings.isNullOrEmpty(dto.structureFloorUuid)) {
            expressions.add(
                cb.equal(
                    root.get<StructureFloor>("structureFloor")
                        .get<String>("uuid"), dto.structureFloorUuid
                )
            )
        }
        if (!Strings.isNullOrEmpty(dto.structureUuid)) {
            expressions.add(
                cb.equal(
                    root.get<StructureFloor>("structureFloor")
                        .get<Structure>("structure")
                        .get<String>("uuid"), dto.structureUuid
                )
            )
        }
        if (!Strings.isNullOrEmpty(dto.uid)) {
            expressions.add(cb.like(root.get("uid"), "%" + (dto.uid) + "%"))
        }
        if (!Strings.isNullOrEmpty(dto.deviceTypeUuid)) {
            expressions.add(
                cb.equal(
                    root.get<Device>("device")
                        .get<DeviceType>("deviceType")
                        .get<String>("uuid"), dto.deviceTypeUuid
                )
            )
        }
        if (dto.hideDisabled) {
            expressions.add(
                cb.isTrue(
                    root.get<Device>("device")
                        .get("enabled")
                )
            )
        }
        expressions.add(
            cb.isFalse(
                root.get<Device>("device")
                    .get("deleted")
            )
        )
        predicate
    }, PageRequest.of(
        dto.page,
        dto.size,
        Sort.by(d.map { Sort.Order.desc(it) }
            .plus(a.map { Sort.Order.asc(it) }))
    )
)