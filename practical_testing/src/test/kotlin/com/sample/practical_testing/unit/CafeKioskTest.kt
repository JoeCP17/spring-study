package com.sample.practical_testing.unit

import com.sample.practical_testing.unit.beverage.Americano
import org.junit.jupiter.api.Test


internal class CafeKioskTest {

    @Test
    fun add() {
        val cafeKiosk = CafeKiosk()
        cafeKiosk.add(Americano())

        println(">>> 담긴 음료 수 : " + cafeKiosk.beverageList.size)
        println(">>> 담긴 음료 : " + cafeKiosk.beverageList[0].getName())
    }
}