package com.sample.practical_testing.unit

import com.sample.practical_testing.unit.beverage.Beverage
import com.sample.practical_testing.unit.order.Order
import java.time.LocalDateTime

/**
 * 음료 계산, 주문 등 키오스크가 담당하는 서비스 클래스\
 * mutableListOf : 추가 , 수정이 가능한 리스트
 * listOf : 읽기 전용 리스트
 */

class CafeKiosk(
        val beverageList: MutableList<Beverage> = mutableListOf()
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

    fun remove(beverage: Beverage) =
        beverageList.remove(beverage)


    fun clear() =
        beverageList.clear()

    fun calculateTotalPrice() : Int {
        var totalPrice = 0
        for (beverage: Beverage in beverageList) {
            totalPrice += beverage.getPrice()
        }

        return totalPrice
    }

    fun createOrder(): Order =
         Order(LocalDateTime.now(), beverageList)


}