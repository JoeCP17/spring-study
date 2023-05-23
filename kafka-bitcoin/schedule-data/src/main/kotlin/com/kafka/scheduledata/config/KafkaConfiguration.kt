package com.kafka.scheduledata.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaConfiguration(

    @Value("\${spring.kafka.producer.bootstrap-servers}")
    val bootstrapAddress: String
) {

    @Bean
    fun producerFactory(): ProducerFactory<String, String> {

        val map: MutableMap<String, String> = HashMap()

        map[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        map[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        map[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        return DefaultKafkaProducerFactory(map as Map<String, Any>)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> =
        KafkaTemplate(producerFactory())

}