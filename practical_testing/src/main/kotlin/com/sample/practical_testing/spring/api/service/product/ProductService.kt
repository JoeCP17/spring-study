package com.sample.practical_testing.spring.api.service.product

import com.sample.practical_testing.spring.api.controller.product.dto.request.ProductCreateRequest
import com.sample.practical_testing.spring.api.service.product.response.ProductResponse
import com.sample.practical_testing.spring.domain.product.ProductRepository
import com.sample.practical_testing.spring.domain.product.ProductSellingStatus
import org.springframework.stereotype.Service

@Service
class ProductService(
    val productRepository: ProductRepository
) {

    fun createProduct(productCreateRequest: ProductCreateRequest) {
        // product Number
        productRepository.findLastestProductNumber()
        // 001 002 003 004
        // DB에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1
        // 009 -> 010
    }

    fun getSellingProducts(): List<ProductResponse> =
        productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay())
            .map { product -> ProductResponse.of(product) }
}