package com.sample.practical_testing.spring.api.service.order

import com.sample.practical_testing.spring.api.service.order.request.OrderCreateRequest
import com.sample.practical_testing.spring.api.service.order.response.OrderResponse
import com.sample.practical_testing.spring.domain.order.Order
import com.sample.practical_testing.spring.domain.order.OrderRepository
import com.sample.practical_testing.spring.domain.product.Product
import com.sample.practical_testing.spring.domain.product.ProductRepository
import com.sample.practical_testing.spring.domain.product.ProductType
import com.sample.practical_testing.spring.domain.stock.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.stream.Collectors

@Service
class OrderService(
    val productRepository: ProductRepository,
    val orderRepository: OrderRepository,
    val stockRepository: StockRepository
) {

    @Transactional
    fun createOrder(request: OrderCreateRequest, registeredDateTime: LocalDateTime): OrderResponse {
        val productNumbers = request.productNumbers

        // Product
        val products = findProductsBy(productNumbers) ?: throw IllegalArgumentException("Product not found")

        // 재고 차감 체크가 필요한 상품들 filter
        val stockProductNumber = products.stream()
            .filter { product -> ProductType.containStockType(product?.type!!) }
            .map { product -> product?.productNumber }
            .collect(Collectors.toList())
        // 재고 Entity 조회
        val stocks = stockRepository.findAllByProductNumberIn(productNumbers)
        val stockMap = stocks.stream()
            .collect(Collectors.toMap({ stock -> stock.productNumber }, { stock -> stock }))

        // 상품별 제고 체크
        val productCountingMap = stockProductNumber.stream()
            .collect(
                Collectors.groupingBy(
                    { productNumber -> productNumber },
                    Collectors.counting()
                )
            )
        // 재고 차감 시도
        stockProductNumber.forEach { productNumber ->
            val stock = stockMap[productNumber]
            val quantity = productCountingMap[productNumber]?.toInt()

            if (stock!!.isQuantityLessThan(quantity!!)) {
                throw IllegalArgumentException("Stock is not enough")
            }

            stock.decreaseQuantity(quantity)

        }

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