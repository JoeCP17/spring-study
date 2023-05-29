package com.sample.practical_testing.spring.domain.product

enum class ProductSellingStatus(
    val text: String
) {

    SELLING("판매중"),
    HOLD("판매 보류"),
    STOP_SELLING("판매 중지");

    companion object {
        fun forDisplay(): List<ProductSellingStatus> {
            return listOf(SELLING, HOLD)
        }
    }
}