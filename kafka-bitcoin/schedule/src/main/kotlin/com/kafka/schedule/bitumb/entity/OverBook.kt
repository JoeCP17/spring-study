package com.kafka.schedule.bitumb.entity

import com.kafka.schedule.bitumb.service.dto.BitumbOrderbookResponseDTO

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class OverBook (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val timestamp: String,
    val paymentCurrency: String,
    val orderCurrency: String,
    @Version
    var version: Long = 1,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
    ){

    companion object {
        fun toEntity(responseDTO: BitumbOrderbookResponseDTO): OverBook {
            return OverBook(
                timestamp = responseDTO.data.timestamp,
                paymentCurrency = responseDTO.data.paymentCurrency,
                orderCurrency = responseDTO.data.orderCurrency,
                createdDate = LocalDateTime.now(),
                updatedDate = LocalDateTime.now()
            )
        }
    }

}