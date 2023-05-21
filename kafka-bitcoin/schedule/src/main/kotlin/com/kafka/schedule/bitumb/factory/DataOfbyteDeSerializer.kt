package com.kafka.schedule.bitumb.factory

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class DataOfbyteDeSerializer(
    val objectMapper: ObjectMapper
) {

    fun <T> deserialize(data: String, clazz: Class<T>): T {
        return objectMapper.readValue(data, clazz)
    }
}