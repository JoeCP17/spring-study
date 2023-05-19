package com.kafka.scheduledata.entity

import org.springframework.data.jpa.repository.JpaRepository

interface BitcoinSymbolRepository : JpaRepository<BitcoinSymbol, Long> {

    fun findBySymbol(symbol: String): BitcoinSymbol

}