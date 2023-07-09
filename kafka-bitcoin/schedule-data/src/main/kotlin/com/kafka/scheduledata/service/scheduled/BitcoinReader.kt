package com.kafka.scheduledata.service.scheduled

import com.kafka.scheduledata.entity.BitcoinSymbol
import com.kafka.scheduledata.entity.BitcoinSymbolRepository
import com.kafka.scheduledata.fetcher.BithumbFetcher
import com.kafka.scheduledata.fetcher.dto.BitumbOrderbookResponseDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.streams.toList

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class BitcoinReader(
    val bithumbFetcher: BithumbFetcher,
    val bitcoinSymbolRepository: BitcoinSymbolRepository
) {
    fun getBitcoinSymbolDataBySavedSymbolList(): List<BitumbOrderbookResponseDTO> =
        getAllBitcoinSymbol().stream()
            .map { bitcoinSymbol -> getBitumbOrderbookData(bitcoinSymbol.symbol) }
            .toList()

    private fun getBitcoinSymbolBySymbolName(symbol: String): BitcoinSymbol {
        return bitcoinSymbolRepository.findBySymbol(symbol)
    }

    private fun getAllBitcoinSymbol(): List<BitcoinSymbol> {
        return bitcoinSymbolRepository.findAll()
    }

    private fun getBitumbOrderbookData(code: String): BitumbOrderbookResponseDTO {
        return bithumbFetcher.getBitumbOrderbook(code)
    }
}