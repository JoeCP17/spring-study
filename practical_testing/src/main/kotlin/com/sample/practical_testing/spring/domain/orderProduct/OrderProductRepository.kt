package com.sample.practical_testing.spring.domain.orderProduct

import org.springframework.data.jpa.repository.JpaRepository

interface OrderProductRepository: JpaRepository<OrderProduct, Long> {
}