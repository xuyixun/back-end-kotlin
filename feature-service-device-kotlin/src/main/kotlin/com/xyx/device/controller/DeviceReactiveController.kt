package com.xyx.device.controller

import com.xyx.device.domain.repository.reactive.DeviceReactiveRepository
import io.swagger.annotations.Api
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.time.Duration

@Api(tags = ["设备-设备"])
@RestController
@RequestMapping("api/reactive/device/device")
class DeviceReactiveController(private val deviceReactiveRepository: DeviceReactiveRepository) {
    @GetMapping(value = ["v1"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun queryAll() =
        Flux.interval(Duration.ofSeconds(5))
            .map {
                deviceReactiveRepository.findAll()
                    .map { it.uid }
            }


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