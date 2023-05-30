package com.sample.practical_testing.spring.api.service.order

import com.sample.practical_testing.spring.domain.order.Order
import com.sample.practical_testing.spring.domain.order.OrderStatus
import com.sample.practical_testing.spring.domain.product.Product
import com.sample.practical_testing.spring.domain.product.ProductSellingStatus
import com.sample.practical_testing.spring.domain.product.ProductType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class OrderTest {

    @Test
    @DisplayName("주문 생성시 상품 리스트에서 주문의 총 금액을 계산한다.")
    fun calculateTotalPrice() {
        // given
        val products = listOf(
            createProduct("001", 1000),
            createProduct("002", 3000),
            createProduct("003", 5000)
        )

        // when
        val order = Order.create(products, LocalDateTime.now())

        // then
        assertThat(order.totalPrice).isEqualTo(9000)
    }


    @Test
    @DisplayName("주문 생성시 주문 상태는 INIT이다.")
    fun init() {
        // given
        val products = listOf(
            createProduct("001", 1000),
            createProduct("002", 3000),
            createProduct("003", 5000)
        )

        // when
        val order = Order.create(products, LocalDateTime.now())

        // then
        assertThat(order.orderStatus).isEqualByComparingTo(OrderStatus.INIT)
    }

    @Test
    @DisplayName("주문 생성시 주문 등록 시간을 기록한다.")
    fun registeredDateTime() {
        // given
        val registeredDateTime = LocalDateTime.now()

        val products = listOf(
            createProduct("001", 1000),
            createProduct("002", 3000),
            createProduct("003", 5000)
        )

        // when
        val order = Order.create(products, registeredDateTime)

        // then
        assertThat(order.orderStatus).isEqualByComparingTo(OrderStatus.INIT)
    }

    private fun createProduct(productNumber: String, price: Int): Product {
        return Product(
            type = ProductType.HANDMADE,
            productNumber = productNumber,
            price = price,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "메뉴 이름"
        )
    }
}