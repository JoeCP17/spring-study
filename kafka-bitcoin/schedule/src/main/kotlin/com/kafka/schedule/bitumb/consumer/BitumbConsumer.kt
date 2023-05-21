package com.kafka.schedule.bitumb.consumer

import com.kafka.schedule.bitumb.factory.DataOfbyteDeSerializer
import com.kafka.schedule.bitumb.service.BitumbService
import com.kafka.schedule.bitumb.service.dto.BitumbOrderbookResponseDTO
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class BitumbConsumer(
    val dataOfbyteDeSerializer: DataOfbyteDeSerializer,
    val bitumbService: BitumbService
) {

    @KafkaListener(topics = ["bitumb"], groupId = "bitumb")
    fun getBitumbOrderBookData(message: String) {
        val deserializeData =
            dataOfbyteDeSerializer.deserialize(message, BitumbOrderbookResponseDTO::class.java)

        bitumbService.saveOrderBookData(deserializeData)
    }

}