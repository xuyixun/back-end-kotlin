package com.xyx.common.domain.repository

import com.xyx.common.domain.po.CommonPoEnabled
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface CommonRepositoryEnable<T : CommonPoEnabled, ID : Any> : JpaRepository<T, ID>

fun <T : (CommonPoEnabled), ID : Any> CommonRepositoryEnable<T, ID>.enable(uuid: ID) = findById(uuid).ifPresent {
    it.enabled = !it.enabled
    save(it)
}
