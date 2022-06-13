package com.xyx.device.controller

import com.xyx.device.domain.repository.reactive.DeviceReactiveRepository
import io.swagger.annotations.Api
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@Api(tags = ["设备-设备"])
@RestController
@RequestMapping("api/reactive/device/device")
class DeviceReactiveController(private val deviceReactiveRepository: DeviceReactiveRepository) {
    @GetMapping("v1")
    fun queryAll() = deviceReactiveRepository.findAll()
        .map { it.uid }

    val webClient = WebClient.builder()
        .baseUrl("http://localhost:8081")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    @GetMapping("test")
    fun test() {
        webClient.get()
            .uri("iot/api/reactive/device/device/v1")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToFlux(
                String::class.java
            )
            .subscribe { println(it) }
    }
}