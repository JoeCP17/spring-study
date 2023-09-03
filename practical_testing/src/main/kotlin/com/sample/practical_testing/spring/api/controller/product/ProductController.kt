package com.sample.practical_testing.spring.api.controller.product

import com.sample.practical_testing.spring.api.controller.product.dto.request.ProductCreateRequest
import com.sample.practical_testing.spring.api.service.product.response.ProductResponse
import com.sample.practical_testing.spring.api.service.product.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val productService: ProductService
) {

    @PostMapping("/api/v1/products/new")
    fun createProduct(productCreateRequest: ProductCreateRequest) {
        productService.createProduct(productCreateRequest)
    }

    @GetMapping("/api/v1/products/selling")
    fun getSellingProducts(): List<ProductResponse> =
        productService.getSellingProducts()


}