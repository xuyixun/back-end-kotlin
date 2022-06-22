package com.xyx.punishment.domain.repository

import com.xyx.file.domain.po.CommonFile
import com.xyx.punishment.domain.po.PunishmentBillFile
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface PunishmentBillFileRepository : JpaRepository<PunishmentBillFile, String> {
    fun deleteByPunishmentBillUuidAndCommonFileUuid(uuid: String, fileUuid: String)

    fun deleteByPunishmentBillUuid(uuid: String)

    fun existsByPunishmentBillUuidAndCommonFileUuidAndWatermarkImgFalse(uuid: String, fileUuid: String): Boolean

    fun existsByPunishmentBillUuidAndCommonFileOriginalImgUuidAndWatermarkImgTrue(uuid: String, fileUuid: String): Boolean

    fun findByPunishmentBillUuid(uuid: String, sort: Sort): Set<PunishmentBillFile>

    @Transactional
    @Modifying
    @Query("update PunishmentBillFile set watermarkImg = true,commonFile = ?2 where uuid = ?1")
    fun updateWatermark(uuid: String, fileUuid: CommonFile)
}