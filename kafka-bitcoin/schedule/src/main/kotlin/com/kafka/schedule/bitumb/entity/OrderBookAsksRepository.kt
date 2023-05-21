package com.kafka.schedule.bitumb.entity

import org.springframework.data.jpa.repository.JpaRepository

interface OrderBookAsksRepository: JpaRepository<OrderBookAsks, Long> {
}