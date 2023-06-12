package com.sample.practical_testing.spring.api.service.order

import com.sample.practical_testing.spring.api.service.order.request.OrderCreateRequest
import com.sample.practical_testing.spring.api.service.order.response.OrderResponse
import com.sample.practical_testing.spring.domain.order.Order
import com.sample.practical_testing.spring.domain.order.OrderRepository
import com.sample.practical_testing.spring.domain.product.Product
import com.sample.practical_testing.spring.domain.product.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.stream.Collectors

@Service
class OrderService(
    val productRepository: ProductRepository,
    val orderRepository: OrderRepository
) {

    @Transactional
    fun createOrder(request: OrderCreateRequest, registeredDateTime: LocalDateTime): OrderResponse {
        val productNumbers = request.productNumbers

        // Product
        val products = findProductsBy(productNumbers) ?: throw IllegalArgumentException("Product not found")

        // Order
        val order = Order.create(products as List<Product>, registeredDateTime)
        val savedOrder = orderRepository.save(order)

        return OrderResponse.of(savedOrder)
    }

    fun findProductsBy(productNumbers: List<String>): MutableList<Product?>? {
        // Product
        val products = productRepository.findAllByProductNumberIn(productNumbers)

        // Product 중복 체크
        val productMap = products.stream()
            .collect(Collectors.toMap({ product -> product.productNumber }, { product -> product }))

        return productNumbers.stream()
            .map { productNumber -> productMap[productNumber] }
            .collect(Collectors.toList())
    }
}