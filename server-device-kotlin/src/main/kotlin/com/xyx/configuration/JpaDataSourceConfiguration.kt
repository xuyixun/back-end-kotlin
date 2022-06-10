package com.xyx.configuration

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

/*
r2dbc和jpa共存时：
jpa的dataSourceScriptDatabaseInitializer bean会被 r2dbc的r2dbcScriptDatabaseInitializer bean代替
jpa的DataSource bean不会自动生成 需要手动注入
 */
@Configuration
class JpaDataSourceConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    fun dataSourcePropertiesJpa(): DataSourceProperties = DataSourceProperties()

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun dataSource(): DataSource = dataSourcePropertiesJpa().initializeDataSourceBuilder()
        .type(HikariDataSource::class.java)
        .build()
}