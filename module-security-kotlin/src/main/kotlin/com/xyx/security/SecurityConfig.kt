package com.xyx.security

import com.xyx.security.filter.AuthorizationFilter
import com.xyx.security.handler.LoginFailureHandler
import com.xyx.security.handler.LoginSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
open class SecurityConfig(private val authorizationFilter: AuthorizationFilter, private val loginSuccessHandler: LoginSuccessHandler) {
    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    open fun securityFilterChainBean(http: HttpSecurity): SecurityFilterChain = http.cors()
        .and()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(
            authorizationFilter, FilterSecurityInterceptor::class.java
        )
        .formLogin()
        .successHandler(loginSuccessHandler)
        .failureHandler(LoginFailureHandler)
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(AuthenticationEntryPointS)
        .accessDeniedHandler(AccessDeniedHandlerS)
        .and()
        .build()

    @Bean
    open fun webSecurityCustomizerBean(): WebSecurityCustomizer = WebSecurityCustomizer { web ->
        web.ignoring()
            .antMatchers("/v2/api-docs", "/swagger-ui/**", "/swagger-resources/**", "/error")
    }

    @Bean
    open fun inMemoryUserDetailsManagerBean(): InMemoryUserDetailsManager =
        InMemoryUserDetailsManager(
            User.builder()
                .passwordEncoder(bCryptPasswordEncoder()::encode)
                .username("user")
                .password("password456")
                .roles("admin")
                .build()
        )

    @Bean
    open fun corsConfigurationSourceBean(): CorsConfigurationSource = UrlBasedCorsConfigurationSource().apply {
        registerCorsConfiguration("/**", CorsConfiguration().apply {
            allowedOrigins = listOf("*")
            allowedMethods = listOf("GET", "POST", "DELETE")
            allowedHeaders = listOf("*")
        })
    }
}