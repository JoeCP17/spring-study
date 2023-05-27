package com.sample.practical_testing.unit

import com.sample.practical_testing.unit.beverage.Americano
import com.sample.practical_testing.unit.beverage.Latte
import com.sample.practical_testing.unit.order.Order
import java.time.LocalDateTime


fun main() {
    run(CafeKiosk())
}

private fun run(cafeKiosk: CafeKiosk) {

    cafeKiosk.add(Americano())
    println(">>> 아메리카노 추가")

    cafeKiosk.add(Latte())
    println(">>> 라떼 추가")

    val totalPrice =cafeKiosk.calculateTotalPrice()
    println(">>> 총 가격은 $totalPrice 입니다.")


    val order: Order = cafeKiosk.createOrder(LocalDateTime.now())
    println(">>> 주문 시간은 ${order.orderDateTime} 입니다.")
}