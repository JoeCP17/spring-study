package com.kafka.schedule.bitumb

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class OrderBookBids(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val quantity: String,
    val price: String,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
) {
}