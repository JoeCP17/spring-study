package com.kafka.schedule.bitumb

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class OverBook (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val timestamp: String,
    val paymentCurrency: String,
    val orderCurrency: String,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
    ){

}