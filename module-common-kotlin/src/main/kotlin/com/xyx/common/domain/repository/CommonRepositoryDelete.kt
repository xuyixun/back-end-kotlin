package com.xyx.common.domain.repository

import com.xyx.common.domain.po.CommonPoDeleted
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.time.LocalDateTime

@NoRepositoryBean
interface CommonRepositoryDelete<T : CommonPoDeleted, ID : Any> : JpaRepository<T, ID>

fun <T : CommonPoDeleted, ID : Any> CommonRepositoryDelete<T, ID>.deleted(uuid: ID) = findById(uuid).ifPresent {
    it.deleted = true
    it.deletedTime = LocalDateTime.now()
    save(it)
}
