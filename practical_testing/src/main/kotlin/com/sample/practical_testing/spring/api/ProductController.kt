package com.sample.practical_testing.spring.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    val productService: ProductService
) {

    @GetMapping("/api/v1/products/selling")
    fun getSellingProducts(): List<ProductResponse> =
        productService.getSellingProducts()


}