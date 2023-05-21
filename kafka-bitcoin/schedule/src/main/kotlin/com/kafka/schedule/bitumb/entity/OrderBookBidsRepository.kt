package com.kafka.schedule.bitumb.entity

import org.springframework.data.jpa.repository.JpaRepository

interface OrderBookBidsRepository: JpaRepository<OrderBookBids, Long> {
}