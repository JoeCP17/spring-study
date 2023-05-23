package com.kafka.schedule.bitumb.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.kafka.schedule.bitumb.service.BitumbService
import com.kafka.schedule.bitumb.service.dto.BitumbOrderbookResponseDTO
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class BitumbConsumer(
    val objectMapper: ObjectMapper,
    val bitumbService: BitumbService
) {

    @KafkaListener(topics = ["bitumb"], groupId = "bitumb")
    fun getBitumbOrderBookData(message: String) {
        val deserializeData =
            objectMapper.deserialize(message, BitumbOrderbookResponseDTO::class.java)

        bitumbService.saveOrderBookData(deserializeData)
    }

    fun <T> ObjectMapper.deserialize(data: String, clazz: Class<T>): T = readValue(data, clazz)


}