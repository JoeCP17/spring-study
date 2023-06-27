package com.sample.practical_testing.spring.api.service.product

import com.sample.practical_testing.spring.domain.product.ProductType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ProductTypeTest {

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    fun test() {
        // given
        val productType = ProductType.HANDMADE

        // when
        val result = ProductType.containStockType(productType)

        // then
        assertThat(result).isFalse
    }

    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    fun test2() {
        // given
        val productType = ProductType.BAKERY

        // when
        val result = ProductType.containStockType(productType)

        // then
        assertThat(result).isTrue
    }
}