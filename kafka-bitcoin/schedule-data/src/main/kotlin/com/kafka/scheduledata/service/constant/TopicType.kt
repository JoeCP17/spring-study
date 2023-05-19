package com.kafka.scheduledata.service.constant


enum class TopicType(
    var topicName: String
) {
    BITUMB("bitumb"),
    COINONE("coinone"),
    UPBIT("upbit")
}