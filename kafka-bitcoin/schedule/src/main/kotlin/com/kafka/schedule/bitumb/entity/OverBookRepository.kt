package com.kafka.schedule.bitumb.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import javax.persistence.LockModeType

interface OverBookRepository: JpaRepository<OverBook, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    fun findByOrderCurrency(name: String): OverBook?
}