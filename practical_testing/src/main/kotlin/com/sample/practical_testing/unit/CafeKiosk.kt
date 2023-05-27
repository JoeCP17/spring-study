package com.sample.practical_testing.unit

import com.sample.practical_testing.unit.beverage.Beverage
import com.sample.practical_testing.unit.order.Order
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * 음료 계산, 주문 등 키오스크가 담당하는 서비스 클래스\
 * mutableListOf : 추가 , 수정이 가능한 리스트
 * listOf : 읽기 전용 리스트
 */

class CafeKiosk(
        val beverageList: MutableList<Beverage> = mutableListOf(),
        private val SHOP_OPEN_TIME: LocalTime = LocalTime.of(10, 0),
        private val SHOP_CLOSE_TIME: LocalTime = LocalTime.of(22, 0),
) {

    fun add(beverage: Beverage) =
        beverageList.add(beverage)

    fun add(beverage: Beverage, count: Int) {
        if(count <= 0) {
            throw IllegalArgumentException("음료 개수는 1개 이상이어야 합니다.")
        }

        for(i in 1..count) {
            beverageList.add(beverage)
        }
    }

    fun calculateTotalPrice() : Int =
        beverageList.sumOf { it.getPrice() }


    fun createOrder(currentDateTime: LocalDateTime): Order {
//        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val currentTime: LocalTime = currentDateTime.toLocalTime()

        if(currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw IllegalStateException("주문 가능 시간이 아닙니다.")
        }

        return Order(currentDateTime, beverageList)
    }


    fun remove(beverage: Beverage) =
        beverageList.remove(beverage)


    fun clear() =
        beverageList.clear()

}