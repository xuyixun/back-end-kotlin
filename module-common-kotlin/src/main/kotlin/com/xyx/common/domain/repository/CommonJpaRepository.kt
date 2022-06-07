package com.xyx.common.domain.repository

import org.springframework.data.jpa.repository.JpaRepository

open class CommonJpaRepository<S : Any, T>(private val jpa: JpaRepository<S, T>) {
    fun jpaSave(s: S) {
        jpa.save(s)
    }
}