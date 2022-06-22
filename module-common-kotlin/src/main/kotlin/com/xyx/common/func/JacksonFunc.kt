package com.xyx.common.func

import com.fasterxml.jackson.databind.ObjectMapper

object JacksonFunc {
    private val OBJECT_MAPPER: ObjectMapper = ObjectMapper()

    fun toJson(value: Any): String = OBJECT_MAPPER.writeValueAsString(value)
}