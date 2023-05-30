package com.sample.practical_testing.spring.api.service.order

import com.sample.practical_testing.spring.api.service.order.request.OrderCreateRequest
import com.sample.practical_testing.spring.api.service.order.response.OrderResponse
import com.sample.practical_testing.spring.domain.order.Order
import com.sample.practical_testing.spring.domain.order.OrderRepository
import com.sample.practical_testing.spring.domain.product.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class OrderService(
    val productRepository: ProductRepository,
    val orderRepository: OrderRepository
) {

    @Transactional
    fun createOrder(request: OrderCreateRequest, registeredDateTime: LocalDateTime): OrderResponse {
        val productNumbers = request.productNumbers
        // Product
        val products = productRepository.findAllByProductNumberIn(productNumbers)

        // Order
        val order = Order.create(products, registeredDateTime)
        val savedOrder = orderRepository.save(order)

        return OrderResponse.of(savedOrder)
    }
}