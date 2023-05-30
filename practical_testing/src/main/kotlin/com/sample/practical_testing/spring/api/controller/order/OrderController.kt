package com.sample.practical_testing.spring.api.controller.order

import com.sample.practical_testing.spring.api.service.order.OrderService
import com.sample.practical_testing.spring.api.service.order.request.OrderCreateRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class OrderController(
    val orderService: OrderService
) {

    @PostMapping("/api/v1/orders/new")
    fun createOrder(@RequestBody request: OrderCreateRequest) {
        val registeredDateTime = LocalDateTime.now()
        orderService.createOrder(request, registeredDateTime)
    }
}