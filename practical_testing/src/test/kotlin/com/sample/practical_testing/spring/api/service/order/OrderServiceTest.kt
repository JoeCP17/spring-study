package com.sample.practical_testing.spring.api.service.order

import com.sample.practical_testing.spring.api.service.order.request.OrderCreateRequest
import com.sample.practical_testing.spring.domain.order.OrderRepository
import com.sample.practical_testing.spring.domain.orderProduct.OrderProductRepository
import com.sample.practical_testing.spring.domain.product.Product
import com.sample.practical_testing.spring.domain.product.ProductRepository
import com.sample.practical_testing.spring.domain.product.ProductSellingStatus
import com.sample.practical_testing.spring.domain.product.ProductType
import com.sample.practical_testing.spring.domain.stock.Stock
import com.sample.practical_testing.spring.domain.stock.StockRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@ActiveProfiles("test")
@SpringBootTest // Spring boot Test는 Transactional이 설정되어있지 않아 roll back이 발생하지 않는다.
@Transactional
internal class OrderServiceTest @Autowired constructor(
    private val orderService: OrderService,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
    private val orderProductRepository: OrderProductRepository,
    private val stockRepository: StockRepository
) {

//    @AfterEach
//    fun tearDown() {
//        productRepository.deleteAllInBatch()
//        orderRepository.deleteAllInBatch()
//        orderProductRepository.deleteAllInBatch()
//    }

    @Test
    @DisplayName("재고와 관련된 상품이 포함되어 있는 주문번호 리스트를 받아 주문을 생성한다.")
    fun createOrderWithStock() {
        // given
        val registeredDateTime = LocalDateTime.now()

        val product1 = createProduct(ProductType.BOTTLE, "001", 1000)
        val product2 = createProduct(ProductType.HANDMADE, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val stock1 = Stock.create("001", 2)
        val stock2 = Stock.create("002", 2)
        stockRepository.saveAll(listOf(stock1, stock2))

        val productNumbers = mutableListOf("001", "001", "002", "003")

        // when
        val orderResponse = orderService.createOrder(OrderCreateRequest(productNumbers), registeredDateTime)

        // then
        assertThat(orderResponse.id).isNotNull
        assertThat(orderResponse)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 10000)

        assertThat(orderResponse.products).hasSize(4)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("001", 1000),
                tuple("002", 3000),
                tuple("003", 5000))

        val stocks = stockRepository.findAll()

        assertThat(stocks).hasSize(2)
            .extracting("productNumber", "quantity")
            .containsExactlyInAnyOrder(
                tuple("001", 0),
                tuple("002", 1))
    }

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

    @DisplayName("중복되는 상품번호 리스트로 주문을 생성할 수 있다.")
    @Test
    fun createOrderWithDuplicateProductNumbers() {
        // given
        val registeredDateTime = LocalDateTime.now()

        val product1 = createProduct(ProductType.HANDMADE, "001", 1000)
        val product2 = createProduct(ProductType.HANDMADE, "002", 3000)
        productRepository.saveAll(listOf(product1, product2))

        val productNumbers = mutableListOf("001", "001")

        // when
        val orderResponse = orderService.createOrder(OrderCreateRequest(productNumbers), registeredDateTime)

        // then
        assertThat(orderResponse.id).isNotNull
        assertThat(orderResponse)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 2000)

        assertThat(orderResponse.products).hasSize(2)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("001", 1000))
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