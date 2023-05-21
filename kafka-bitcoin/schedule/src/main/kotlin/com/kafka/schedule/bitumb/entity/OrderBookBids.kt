package com.kafka.schedule.bitumb.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class OrderBookBids(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val quantity: String,
    val price: String,
    @ManyToOne(targetEntity = OverBook::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "over_book_id")
    val overBookId: OverBook,
    val createdDate: LocalDateTime,
    val updatedDate: LocalDateTime
) {

    companion object {
        fun toEntity(quantity: String, price: String, overBook: OverBook): OrderBookBids {
            return OrderBookBids(
                quantity = quantity,
                price = price,
                overBookId = overBook,
                createdDate = LocalDateTime.now(),
                updatedDate = LocalDateTime.now()
            )
        }
    }
}