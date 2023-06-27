package com.sample.practical_testing.repository

import com.sample.practical_testing.spring.PracticalTestingApplication
import com.sample.practical_testing.spring.domain.product.Product
import com.sample.practical_testing.spring.domain.product.ProductSellingStatus
import com.sample.practical_testing.spring.domain.product.ProductType
import com.sample.practical_testing.spring.domain.stock.Stock
import com.sample.practical_testing.spring.domain.stock.StockRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [PracticalTestingApplication::class])
class StockRepositoryTest @Autowired constructor(
     private val stockRepository: StockRepository
) {

    @Test
    @DisplayName("상품번호 리스트로 재고를 조회한다.")
    fun findAllByProductNumberIn() {
        // given
        val stock1 = Stock.create("001", 1)
        val stock2 = Stock.create("002", 2)
        val stock3 = Stock.create("003", 3)
        stockRepository.saveAll(listOf(stock1, stock2, stock3))

        // when
        val stocks = stockRepository.findAllByProductNumberIn(listOf("001", "002"))

        // then
        Assertions.assertThat(stocks).hasSize(2)
            .extracting("productNumber", "quantity")
            .containsExactlyInAnyOrder(
                Assertions.tuple("001", 1),
                Assertions.tuple("002", 2)
            )
    }
}