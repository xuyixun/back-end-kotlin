package com.xyx

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableR2dbcRepositories("**.repository.reactive")
@EnableJpaRepositories( "**.repository.jpa")
@EnableRedisRepositories
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
