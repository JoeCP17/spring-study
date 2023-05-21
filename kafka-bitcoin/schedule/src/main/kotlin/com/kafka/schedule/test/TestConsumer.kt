package com.kafka.schedule.test

import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class TestConsumer {

    // 다음과 같이 topic, groupId를 설정해 두면 해당 topic에 대한 메시지가 존재할 시 메시지를 가져온다.
    @KafkaListener(topics = ["test"], groupId = "bitumb")
    fun consumeMessageTest(message: String) {
        println("Consumed message: $message")
    }

}