package com.example.infrainterface

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InfraCommonApplication

fun main(args: Array<String>) {
    runApplication<InfraCommonApplication>(*args)
}
