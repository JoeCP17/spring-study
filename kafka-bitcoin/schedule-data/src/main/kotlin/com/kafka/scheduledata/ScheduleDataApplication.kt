package com.kafka.scheduledata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class ScheduleDataApplication

fun main(args: Array<String>) {
    runApplication<ScheduleDataApplication>(*args)
}
