package com.xyx.punishment.domain.repository

import com.xyx.file.domain.po.CommonFileType
import com.xyx.punishment.domain.po.PunishmentBillFile
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface PunishmentBillFileRepository : JpaRepository<PunishmentBillFile, String> {
    fun deleteByPunishmentBillUuidAndCommonFileUuid(uuid: String, fileUuid: String)

    fun deleteByPunishmentBillUuid(uuid: String)

    fun existsByPunishmentBillUuidAndCommonFileUuid(uuid: String, fileUuid: String): Boolean

    fun findByPunishmentBillUuid(uuid: String, sort: Sort): Set<PunishmentBillFile>

    fun findByPunishmentBillUuidAndCommonFileType(uuid: String, type: CommonFileType, sort: Sort): Set<PunishmentBillFile>
}