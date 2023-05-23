package com.kafka.schedule.bitumb.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.kafka.schedule.bitumb.entity.*
import com.kafka.schedule.bitumb.service.dto.BitumbOrderbookResponseDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BitumbService(
    val orderBookAsksRepository: OrderBookAsksRepository,
    val orderBookBidsRepository: OrderBookBidsRepository,
    val overBookRepository: OverBookRepository,
    val objectMapper: ObjectMapper
) {

    @Transactional
    fun saveOrderBookData(response: BitumbOrderbookResponseDTO) {
        val findOrderBook = findOrderBookByOrderCurrency(response.data.orderCurrency)

        findOrderBook?.run {
            saveOrderBookAsks(response, this)
            saveOrderBookBids(response, this)

        } ?: run {
            val overBook = saveOverBook(response)
            saveOrderBookAsks(response, overBook)
            saveOrderBookBids(response, overBook)
        }
    }

    private fun saveOverBook(response: BitumbOrderbookResponseDTO): OverBook {
        return toOverBookEntityBy(response).let {
            overBookRepository.save(it)
        }
    }

    private fun saveOrderBookAsks(response: BitumbOrderbookResponseDTO, overBook: OverBook) {
        response.data.asks.forEach { it ->
            toAsksEntityBy(it.quantity, it.price, overBook).let {
                orderBookAsksRepository.save(it)
            }
        }
    }

    private fun saveOrderBookBids(response: BitumbOrderbookResponseDTO, overBook: OverBook) {
        response.data.bids.forEach { it ->
            toBidsEntityBy(it.quantity, it.price, overBook).let {
                orderBookBidsRepository.save(it)
            }
        }
    }

    private fun toOverBookEntityBy(response: BitumbOrderbookResponseDTO): OverBook =
        OverBook.toEntity(response)


    private fun toAsksEntityBy(quantity: String, price: String, overBook: OverBook): OrderBookAsks =
        OrderBookAsks.toEntity(quantity, price, overBook)


    private fun toBidsEntityBy(quantity: String, price: String, overBook: OverBook): OrderBookBids =
        OrderBookBids.toEntity(quantity, price, overBook)



    // 값이 없을 경우 save 처리를 하기 위해 ?로 설정
    private fun findOrderBookByOrderCurrency(name: String): OverBook? =
        overBookRepository.findByOrderCurrency(name)

}