package com.sample.practical_testing.spring.domain.product

enum class ProductType(
    val description: String
) {

    HANDMADE("수제음료"),
    BOTTLE("병음료"),
    BAKERY("베이커리");


    companion object {
        fun containStockType(type: ProductType): Boolean {
            return listOf(BAKERY, BOTTLE).contains(type)
        }
    }
}