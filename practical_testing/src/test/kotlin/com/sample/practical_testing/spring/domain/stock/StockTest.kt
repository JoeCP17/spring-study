package com.sample.practical_testing.spring.domain.stock

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class StockTest {

    @Test
    @DisplayName("재고의 수량이 제공된 수량보다 작은지 확인한다.")
    fun isQuantityLessThan() {
        // given
        val stock = Stock.create("001", 1)
        val quantity = 2

        // when
        val result = stock.isQuantityLessThan(quantity)

        // then
        assertThat(result).isTrue
    }

    @Test
    @DisplayName("재고를 주어진 개수만큼 차감할 수 있다.")
    fun deductQuantity() {
        // given
        val stock = Stock.create("001", 1)
        val quantity = 1

        // when
        stock.decreaseQuantity(quantity)

        // then
        assertThat(stock.quantity).isZero
    }

    @Test
    @DisplayName("재고보다 많은 수의 수량으로 차감을 시도하는 경우 예외가 발생한다.")
    fun deductQuantity2() {
        // given
        val stock = Stock.create("001", 1)
        val quantity = 2

        // when & then
        assertThatThrownBy { stock.decreaseQuantity(quantity) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("재고가 부족합니다.")
    }
}