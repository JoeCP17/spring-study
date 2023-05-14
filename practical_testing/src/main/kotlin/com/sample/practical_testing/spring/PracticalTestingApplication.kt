package com.sample.practical_testing.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PracticalTestingApplication

fun main(args: Array<String>) {
    runApplication<PracticalTestingApplication>(*args)
}
