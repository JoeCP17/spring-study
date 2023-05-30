package com.sample.practical_testing.repository

import com.sample.practical_testing.spring.PracticalTestingApplication
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

@ActiveProfiles("test")
@SpringBootTest(classes = [PracticalTestingApplication::class])
//@DataJpaTest // Spring Boot Test 보다 가볍고 JPA관련 설정만 로드하기때문에 빠르다.
internal class ProductRepositoryTest @Autowired constructor(
    private val productRepository: ProductRepository
) {

    @Test
    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    fun findAllBySellingStatusIn() {
        // given
        val product = Product(
            productNumber = "001",
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "아메리카노",
            price = 4000
        )

        val product2 = Product(
            productNumber = "002",
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.HOLD,
            name = "카페라떼",
            price = 4500
        )

        val product3 = Product(
            productNumber = "003",
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.STOP_SELLING,
            name = "팥빙",
            price = 7000
        )
        productRepository.saveAll(listOf(product, product2, product3))
        // when
        val products = productRepository.findAllBySellingStatusIn(
            listOf(
                ProductSellingStatus.SELLING,
                ProductSellingStatus.HOLD
            )
        )

        // then
        assertThat(products).hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", ProductSellingStatus.SELLING),
                tuple("002", "카페라떼", ProductSellingStatus.HOLD)
            )
    }

    @Test
    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    fun findAllByProductNumberIn() {
        // given
        val product = Product(
            productNumber = "001",
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.SELLING,
            name = "아메리카노",
            price = 4000
        )

        val product2 = Product(
            productNumber = "002",
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.HOLD,
            name = "카페라떼",
            price = 4500
        )

        val product3 = Product(
            productNumber = "003",
            type = ProductType.HANDMADE,
            sellingStatus = ProductSellingStatus.STOP_SELLING,
            name = "팥빙",
            price = 7000
        )
        productRepository.saveAll(listOf(product, product2, product3))
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
                tuple("001", "아메리카노", ProductSellingStatus.SELLING),
                tuple("002", "카페라떼", ProductSellingStatus.HOLD)
            )
    }
}