package com.kafka.scheduledata.service

import com.kafka.scheduledata.controller.dto.AddCoinSymbolResponse
import com.kafka.scheduledata.controller.dto.AddCoinsymbolRequest
import com.kafka.scheduledata.entity.BitcoinSymbol
import com.kafka.scheduledata.entity.BitcoinSymbolRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class BitcoinService(
    val kafkaTemplate: KafkaTemplate<String, String>,
    val bitcoinSymbolRepository: BitcoinSymbolRepository
) {

    fun sendMessageTest(message: String) {
        println("Sending message to kafka: $message")
        kafkaTemplate.send("test", message)
    }

    @Transactional
    fun addCoinSymbol(symbol: AddCoinsymbolRequest): AddCoinSymbolResponse {
         BitcoinSymbol.of(symbol).let { bitcoinSymbol ->
            return AddCoinSymbolResponse.of( saveBitcoinSymbol(bitcoinSymbol))
        }
    }

    private fun saveBitcoinSymbol(bitcoinSymbol: BitcoinSymbol): BitcoinSymbol {
        return bitcoinSymbolRepository.save(bitcoinSymbol)
    }
}