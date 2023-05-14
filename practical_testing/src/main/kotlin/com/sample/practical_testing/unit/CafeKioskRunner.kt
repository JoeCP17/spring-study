package com.sample.practical_testing.unit

import com.sample.practical_testing.unit.beverage.Americano
import com.sample.practical_testing.unit.beverage.Latte


fun main(args: Array<String>) {
    run(CafeKiosk())
}

private fun run(cafeKiosk: CafeKiosk) {

    cafeKiosk.add(Americano())
    println(">>> 아메리카노 추가")

    cafeKiosk.add(Latte())
    println(">>> 라떼 추가")

    val totalPrice =cafeKiosk.calculateTotalPrice()
    println(">>> 총 가격은 $totalPrice 입니다.")
}