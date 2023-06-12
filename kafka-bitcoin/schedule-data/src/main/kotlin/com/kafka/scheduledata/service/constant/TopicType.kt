package com.kafka.scheduledata.service.constant


enum class TopicType(
    var topicName: String
) {
    BITHUMB("bithumb"),
    COINONE("coinone"),
    UPBIT("upbit")
}