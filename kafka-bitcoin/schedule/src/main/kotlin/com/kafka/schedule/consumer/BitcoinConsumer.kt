package com.kafka.schedule.consumer

import lombok.extern.slf4j.Slf4j
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class BitcoinConsumer {

    // 다음과 같이 topic, groupId를 설정해 두면 해당 topic에 대한 메시지가 존재할 시 메시지를 가져온다.
    @KafkaListener(topics = ["test"], groupId = "bitumb")
    fun consumeMessageTest(message: String) {
        println("Consumed message: $message")
    }
}