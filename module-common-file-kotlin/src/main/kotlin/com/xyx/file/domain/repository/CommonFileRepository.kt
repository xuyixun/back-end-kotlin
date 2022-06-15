package com.xyx.file.domain.repository

import com.xyx.file.domain.po.CommonFile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CommonFileRepository : JpaRepository<CommonFile, String> {
    fun findBySha256(sha256: String): Optional<CommonFile>
}