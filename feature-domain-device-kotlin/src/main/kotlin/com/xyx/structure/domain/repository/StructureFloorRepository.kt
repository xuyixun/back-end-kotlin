package com.xyx.structure.domain.repository

import com.xyx.common.domain.repository.CommonRepositoryDelete
import com.xyx.structure.domain.po.StructureFloor
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface StructureFloorRepository : JpaRepository<StructureFloor, String>, CommonRepositoryDelete<StructureFloor, String> {
    fun findByStructureUuidAndDeletedFalse(uuid: String, sort: Sort)
}