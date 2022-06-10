package com.xyx.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.yaml.snakeyaml.Yaml
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SpringFoxConfig {
    @Value("\${host}")
    lateinit var host: String

    private fun apiKey(): ApiKey {
        val yaml =
            Yaml().load<Map<String, Any>>(this.javaClass.classLoader.getResourceAsStream("config-jwt.yml"))
        return ApiKey("JWT", yaml["token_header"].toString(), "header")
    }

    private fun defaultAuth(): List<SecurityReference> = listOf(SecurityReference("JWT", arrayOf(AuthorizationScope("global", "accessEverything"))))

    private fun securityContext(): SecurityContext? = SecurityContext.builder()
        .securityReferences(defaultAuth())
        .operationSelector { true }
        .build()

    @Bean
    fun api(): Docket =
        Docket(DocumentationType.SWAGGER_2)
            .forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(listOf(apiKey()))
            .securityContexts(listOf(securityContext()))
            .host(host)
}