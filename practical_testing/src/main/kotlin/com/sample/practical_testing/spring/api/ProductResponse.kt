package com.sample.practical_testing.spring.api

import com.sample.practical_testing.spring.domain.product.Product
import com.sample.practical_testing.spring.domain.product.ProductSellingStatus
import com.sample.practical_testing.spring.domain.product.ProductType

class ProductResponse(
    val id: Long,

    val productNumber: String,

    val productType: ProductType,

    val productSellingStatus: ProductSellingStatus,

    val name: String,

    val price: Int
) {



    companion object {
        fun of(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id!!,
                productNumber = product.productNumber,
                productType = product.type,
                productSellingStatus = product.sellingStatus,
                name = product.name,
                price = product.price
            )
        }
    }
}