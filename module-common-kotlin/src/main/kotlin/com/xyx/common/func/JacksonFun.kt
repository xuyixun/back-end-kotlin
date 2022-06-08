package com.xyx.common.func

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

object JacksonFun {
    private val OBJECT_MAPPER: ObjectMapper = ObjectMapper()

    fun toJson(value: Any): String? {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (e: JsonProcessingException) {
            e.printStackTrace();
        }
        return null;
    }
}