package com.sample.practical_testing.practice

fun handleConrty(contry: Contry) {
    when (contry) {
        Contry.KOREA -> {
            println("한국")
        }

        Contry.AMERICA -> {
            println("미국")
        }
    }
}

enum class Contry(
    private val code: String,
) {
    KOREA("KO"),
    AMERICA("US");
}