package com.sample.practical_testing.spring.api.service.order.response

import com.sample.practical_testing.spring.api.service.product.response.ProductResponse
import com.sample.practical_testing.spring.domain.order.Order
import java.time.LocalDateTime

data class OrderResponse(

    val id: Long? = null,

    val totalPrice: Int,

    val registeredDateTime: LocalDateTime,

    val products: List<ProductResponse>

) {
    companion object {
        fun of(order: Order): OrderResponse {
            return OrderResponse(
                id = order.id,
                totalPrice = order.totalPrice,
                registeredDateTime = order.registeredDateTime!!,
                products = order.orderProducts!!.map { orderProduct -> ProductResponse.of(orderProduct.product) }
            )
        }
    }
}
