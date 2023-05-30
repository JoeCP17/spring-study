package com.sample.practical_testing.spring.api.service.order.request

data class OrderCreateRequest(
    val productNumbers: MutableList<String>
) {
}