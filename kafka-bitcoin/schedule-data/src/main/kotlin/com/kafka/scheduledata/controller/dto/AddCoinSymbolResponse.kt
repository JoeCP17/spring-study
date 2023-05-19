package com.kafka.scheduledata.controller.dto

import com.kafka.scheduledata.entity.BitcoinSymbol
import java.time.LocalDateTime

data class AddCoinSymbolResponse(
    val id: Long,
    val symbol: String,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
) {

    companion object {
        fun of(entity: BitcoinSymbol): AddCoinSymbolResponse {
            return AddCoinSymbolResponse(
                id = entity.id!!,
                symbol = entity.symbol,
                createdDate = entity.createdDate,
                updatedDate = entity.updatedDate
            )
        }
    }
}