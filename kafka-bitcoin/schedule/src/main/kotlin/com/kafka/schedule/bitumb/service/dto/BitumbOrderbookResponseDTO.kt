package com.kafka.schedule.bitumb.service.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.kafka.schedule.bitumb.entity.OrderBookAsks
import com.kafka.schedule.bitumb.entity.OverBook
import java.time.LocalDateTime

data class BitumbOrderbookResponseDTO(
    val status: String,
    val data: BithumbOrderbookDataDTO
) {
    data class BithumbOrderbookDataDTO(
        val timestamp: String,
        @JsonProperty("payment_currency")
        val paymentCurrency: String, // 주문통화 ( 코인 )
        @JsonProperty("order_currency")
        val orderCurrency: String, // 결제 통화 ( 마켓 )
        val bids: List<BithumbOrderbookBidsDTO>, // 매수 요청 내역
        val asks: List<BithumbOrderbookAsksDTO> // 매도 요청 내역
    ) {

        data class BithumbOrderbookBidsDTO(
            val quantity: String, // 수량
            val price: String // 가격
        )

        data class BithumbOrderbookAsksDTO(
            val quantity: String,
            val price: String
        ) {
            companion object {
                fun toEntity(asks: BithumbOrderbookAsksDTO, overBook: OverBook): OrderBookAsks {
                    return OrderBookAsks(
                        quantity = asks.quantity,
                        price = asks.price,
                        overBookId = overBook,
                        createdDate = LocalDateTime.now(),
                        updatedDate = LocalDateTime.now()
                    )
                }
            }
        }
    }
}