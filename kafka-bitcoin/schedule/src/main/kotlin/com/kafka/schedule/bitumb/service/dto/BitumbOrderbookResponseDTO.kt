package com.kafka.schedule.bitumb.service.dto

import com.kafka.schedule.bitumb.entity.OrderBookAsks
import com.kafka.schedule.bitumb.entity.OverBook
import java.time.LocalDateTime

data class BitumbOrderbookResponseDTO(
    val status: String,
    val data: BithumbOrderbookDataDTO
) {
    data class BithumbOrderbookDataDTO(
        val timestamp: String,
        val payment_currency: String, // 주문통화 ( 코인 )
        val order_currency: String, // 결제 통화 ( 마켓 )
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

    companion object {

        fun getBids(data: BithumbOrderbookDataDTO.BithumbOrderbookBidsDTO): BithumbOrderbookDataDTO.BithumbOrderbookBidsDTO {
            return data
        }

        fun getAsks(data: BithumbOrderbookDataDTO.BithumbOrderbookAsksDTO, index: Int): BithumbOrderbookDataDTO.BithumbOrderbookAsksDTO {
            return data
        }
    }
}