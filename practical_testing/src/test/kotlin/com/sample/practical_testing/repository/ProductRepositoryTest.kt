package com.sample.practical_testing.repository

import com.sample.practical_testing.spring.PracticalTestingApplication
import com.sample.practical_testing.spring.domain.product.Product
import com.sample.practical_testing.spring.domain.product.ProductRepository
import com.sample.practical_testing.spring.domain.product.ProductSellingStatus
import com.sample.practical_testing.spring.domain.product.ProductSellingStatus.*
import com.sample.practical_testing.spring.domain.product.ProductType
import com.sample.practical_testing.spring.domain.product.ProductType.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(classes = [PracticalTestingApplication::class])
//@DataJpaTest // Spring Boot Test 보다 가볍고 JPA관련 설정만 로드하기때문에 빠르다.
internal class ProductRepositoryTest @Autowired constructor(
    private val productRepository: ProductRepository
) {

    @AfterEach
    fun tearDown() {
        productRepository.deleteAll()
    }

    @Test
    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    fun findAllBySellingStatusIn() {
        // given
        val product1 = createProduct(
            productNumber = "001",
            handMade = HANDMADE,
            status = SELLING,
            name = "아메리카노",
            price = 4000
        )
        val product2 = createProduct(
            productNumber = "002",
            handMade = HANDMADE,
            status = HOLD,
            name = "카페라떼",
            price = 4500
        )
        val product3 = createProduct(
            productNumber = "003",
            handMade = HANDMADE,
            status = STOP_SELLING,
            name = "팥빙수",
            price = 4000
        )

        productRepository.saveAll(listOf(product1, product2, product3))
        // when
        val products = productRepository.findAllBySellingStatusIn(
            listOf(
                SELLING,
                HOLD
            )
        )

        // then
        assertThat(products).hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", SELLING),
                tuple("002", "카페라떼", HOLD)
            )
    }

    @Test
    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    fun findAllByProductNumberIn() {
        // given
        val product1 = createProduct(
            productNumber = "001",
            handMade = HANDMADE,
            status = SELLING,
            name = "아메리카노",
            price = 4000
        )
        val product2 = createProduct(
            productNumber = "002",
            handMade = HANDMADE,
            status = HOLD,
            name = "카페라떼",
            price = 4500
        )
        val product3 = createProduct(
            productNumber = "003",
            handMade = HANDMADE,
            status = STOP_SELLING,
            name = "팥빙수",
            price = 4000
        )

        productRepository.saveAll(listOf(product1, product2, product3))
        // when
        val products = productRepository.findAllByProductNumberIn(
            listOf(
                "001",
                "002"
            )
        )

        // then
        assertThat(products).hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", SELLING),
                tuple("002", "카페라떼", HOLD)
            )
    }

    @Test
    fun 가장_마지막으로_저장한_상품의_상품번호를_읽어온다() {
        // given
        val targetProductNumber = "003"

        // given
        val product1 = createProduct(
            productNumber = "001",
            handMade = HANDMADE,
            status = SELLING,
            name = "아메리카노",
            price = 4000
        )
        val product2 = createProduct(
            productNumber = "002",
            handMade = HANDMADE,
            status = HOLD,
            name = "카페라떼",
            price = 4500
        )
        val product3 = createProduct(
            productNumber = "003",
            handMade = HANDMADE,
            status = STOP_SELLING,
            name = "팥빙수",
            price = 4000
        )

        productRepository.saveAll(listOf(product1, product2, product3))

        // when
        val findLastestProductNumber = productRepository.findLastestProductNumber()

        // then
        assertThat(findLastestProductNumber).isEqualTo(targetProductNumber)
    }

    @Test
    fun 가장_마지막으로_저장한_상품의_상품번호를_읽어올_때_상품이_하나도_없는_경우에는_null을_반환한다() {
        // when
        val findLastestProductNumber = productRepository.findLastestProductNumber()

        // then
        assertThat(findLastestProductNumber).isNull()
    }

    private fun createProduct(
        productNumber: String,
        handMade: ProductType,
        status: ProductSellingStatus,
        name: String,
        price: Int
    ): Product {
        return Product(
            productNumber = productNumber,
            type = handMade,
            sellingStatus = status,
            name = name,
            price = price
        )
    }
}
