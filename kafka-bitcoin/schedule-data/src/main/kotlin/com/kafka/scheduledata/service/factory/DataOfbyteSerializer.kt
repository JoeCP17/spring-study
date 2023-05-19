package com.kafka.scheduledata.service.factory

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class DataOfbyteSerializer(
    val objectMapper: ObjectMapper
) {
    fun <T> serialize(data: T): String {
        return objectMapper.writeValueAsString(data)
    }
}