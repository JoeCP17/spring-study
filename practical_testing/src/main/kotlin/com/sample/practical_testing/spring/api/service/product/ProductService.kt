package com.sample.practical_testing.spring.api.service.product

import com.sample.practical_testing.spring.api.service.product.response.ProductResponse
import com.sample.practical_testing.spring.domain.product.ProductRepository
import com.sample.practical_testing.spring.domain.product.ProductSellingStatus
import org.springframework.stereotype.Service

@Service
class ProductService(
    val productRepository: ProductRepository
) {

    fun getSellingProducts(): List<ProductResponse> =
        productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay())
            .map { product -> ProductResponse.of(product) }

}