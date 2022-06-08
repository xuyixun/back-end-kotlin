package com.xyx.common.domain.service

import org.springframework.data.jpa.repository.JpaRepository

open class CommonService<S : Any, T>(private val jpa: JpaRepository<S, T>) {
    fun jpaSave(s: S) {
        jpa.save(s)
    }
}