package com.kafka.scheduledata.entity

import com.kafka.scheduledata.controller.dto.AddCoinsymbolRequest
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class BitcoinSymbol(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val symbol: String,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
) {

    companion object {
        fun of(symbolRequest: AddCoinsymbolRequest): BitcoinSymbol {
            return BitcoinSymbol(
                symbol = symbolRequest.symbol,
                createdDate = LocalDateTime.now(),
                updatedDate = LocalDateTime.now()
            )
        }
    }
}