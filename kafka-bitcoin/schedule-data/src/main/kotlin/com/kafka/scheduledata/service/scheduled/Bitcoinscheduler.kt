package com.kafka.scheduledata.service.scheduled

import com.fasterxml.jackson.databind.ObjectMapper
import com.kafka.scheduledata.service.constant.TopicType
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class Bitcoinscheduler(
    val bitcoinReader: BitcoinReader,
    val kafkaTemplate: KafkaTemplate<String, String>,
    val objectMapper: ObjectMapper
    ) {

    /**
     * @author : Ueibin
     * @description : 빗썸에서 제공하는 코인 Symbol들을 가져온 후, kafka를 통해 토픽을 전송한다.
     * @scope : 10초마다 실행
     * @date : 2023.05.20
     */
    @Transactional
    @Scheduled(cron = "10 * * * * *")
    fun getBitumbOrderbookData() {
        // reader : 저장된 Coin Symbol들을 기반으로 빗썸에서 데이터를 가져온다.
        val bitcoinSymbolDataBySavedSymbolList =
            bitcoinReader.getBitcoinSymbolDataBySavedSymbolList()

        // 전달받은 데이터를 기반으로 직렬화를 거쳐 topic 발송을 요청한다.
        bitcoinSymbolDataBySavedSymbolList.forEach {response ->
            kafkaTemplate.send(TopicType.BITHUMB.topicName, objectMapper.serialize(response))
        }
    }

    fun <T> ObjectMapper.serialize(data: T): String = writeValueAsString(data)

}