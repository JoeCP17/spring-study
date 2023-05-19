package com.kafka.scheduledata.controller

import com.kafka.scheduledata.controller.dto.AddCoinSymbolResponse
import com.kafka.scheduledata.controller.dto.AddCoinsymbolRequest
import com.kafka.scheduledata.service.BitcoinService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class BitcoinController(
    val bitcoinService: BitcoinService
) {

    @PostMapping("/test/message")
    fun sendMessageTest(message: String) {
        bitcoinService.sendMessageTest(message)
    }

    @PostMapping("/add/coin-symbol")
    fun addCoinSymbol(@RequestBody symbol: AddCoinsymbolRequest): AddCoinSymbolResponse {
        return bitcoinService.addCoinSymbol(symbol)
    }
}