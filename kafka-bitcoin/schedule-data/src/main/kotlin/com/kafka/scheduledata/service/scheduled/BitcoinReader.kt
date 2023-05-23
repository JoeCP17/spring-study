package com.kafka.scheduledata.service.scheduled

import com.kafka.scheduledata.entity.BitcoinSymbol
import com.kafka.scheduledata.entity.BitcoinSymbolRepository
import com.kafka.scheduledata.fetcher.BitumbFetcher
import com.kafka.scheduledata.fetcher.dto.BitumbOrderbookResponseDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.streams.toList

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class BitcoinReader(
    val bitumbFetcher: BitumbFetcher,
    val bitcoinSymbolRepository: BitcoinSymbolRepository
) {
    fun getBitcoinSymbolDataBySavedSymbolList(): List<BitumbOrderbookResponseDTO> =
        getAllBitcoinSymbol().stream()
            .map { bitcoinSymbol -> getBitumbOrderbookData(bitcoinSymbol.symbol) }
            .toList()

    fun getBitcoinSymbolBySymbolName(symbol: String): BitcoinSymbol {
        return bitcoinSymbolRepository.findBySymbol(symbol)
    }

    fun getAllBitcoinSymbol(): List<BitcoinSymbol> {
        return bitcoinSymbolRepository.findAll()
    }

    fun getBitumbOrderbookData(code: String): BitumbOrderbookResponseDTO {
        return bitumbFetcher.getBitumbOrderbook(code)
    }
}