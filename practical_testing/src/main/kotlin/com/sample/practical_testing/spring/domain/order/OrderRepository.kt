package com.sample.practical_testing.spring.domain.order

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long> {
}