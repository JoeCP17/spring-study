package com.sample.practical_testing.spring.api.service.order

import com.sample.practical_testing.spring.api.service.order.request.OrderCreateRequest
import com.sample.practical_testing.spring.domain.product.Product
import com.sample.practical_testing.spring.domain.product.ProductRepository
import com.sample.practical_testing.spring.domain.product.ProductSellingStatus
import com.sample.practical_testing.spring.domain.product.ProductType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime

@ActiveProfiles("test")
@SpringBootTest
internal class OrderServiceTest @Autowired constructor(
    private val orderService: OrderService,
    private val productRepository: ProductRepository
) {

    @Test
    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    fun createOrder() {
        // given
        val registeredDateTime = LocalDateTime.now()

        val product1 = createProduct(ProductType.HANDMADE, "001", 1000)
        val product2 = createProduct(ProductType.HANDMADE, "002", 3000)
        productRepository.saveAll(listOf(product1, product2))

        val productNumbers = mutableListOf("001", "002")

        // when
        val orderResponse = orderService.createOrder(OrderCreateRequest(productNumbers), registeredDateTime)

        // then
        assertThat(orderResponse.id).isNotNull
        assertThat(orderResponse)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 4000)

        assertThat(orderResponse.products).hasSize(2)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("002", 3000))
    }

    private fun createProduct(type:ProductType, productNumber: String, price: Int): Product {
        return Product(
            type = type,
            productNumber = productNumber,
            price = price,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "아메리카노"
        )
    }

}