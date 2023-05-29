package com.sample.practical_testing.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class PracticalTestingApplication

fun main(args: Array<String>) {
    runApplication<PracticalTestingApplication>(*args)
}
