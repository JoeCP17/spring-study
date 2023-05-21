package com.kafka.schedule.bitumb.entity

import org.springframework.data.jpa.repository.JpaRepository

interface OverBookRepository: JpaRepository<OverBook, Long> {

    fun findByOrderCurrency(name: String): OverBook?
}