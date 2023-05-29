package com.sample.practical_testing.unit

import com.sample.practical_testing.practice.unit.CafeKiosk
import com.sample.practical_testing.practice.unit.beverage.Americano
import com.sample.practical_testing.practice.unit.beverage.Latte
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


internal class CafeKioskTest {

    @Test
    fun add() {
        val cafeKiosk = CafeKiosk()
        cafeKiosk.add(Americano())

        println(">>> 담긴 음료 수 : " + cafeKiosk.beverageList.size)
        println(">>> 담긴 음료 : " + cafeKiosk.beverageList[0].getName())
    }

    @Test
    @DisplayName("음료 1개를 추가하면 주문 목록에 담긴다.")
    fun add_auto() {
        val cafeKiosk = CafeKiosk()
        cafeKiosk.add(Americano())

        assertEquals(1, cafeKiosk.beverageList.size)
        assertEquals("아메리카노", cafeKiosk.beverageList[0].getName())
    }

    @Test
    @DisplayName("자동 테스트 - 해피케이스")
    fun add_auto_several_beverages() {
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        cafeKiosk.add(americano, 2)

        assertThat(cafeKiosk.beverageList[0]).isEqualTo(americano)
        assertThat(cafeKiosk.beverageList[1]).isEqualTo(americano)
    }

    @Test
    @DisplayName("자동 테스트 - 예외케이스")
    fun add_auto_zero_beverages() {
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        assertThatThrownBy { cafeKiosk.add(americano, 0) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("음료 개수는 1개 이상이어야 합니다.")
    }

    @Test
    fun remove() {
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        cafeKiosk.add(americano)
        assertThat(cafeKiosk.beverageList).hasSize(1)

        cafeKiosk.remove(americano)
        assertThat(cafeKiosk.beverageList).isEmpty()
    }

    @Test
    fun clear() {
        val cafeKiosk = CafeKiosk()
        val americano = Americano()
        val latte = Latte()

        cafeKiosk.add(americano)
        cafeKiosk.add(latte)

        assertThat(cafeKiosk.beverageList).hasSize(2)

        cafeKiosk.clear()
        assertThat(cafeKiosk.beverageList).isEmpty()
    }

    @Test
    fun getName() {
        val americano = Americano()
        assertEquals("아메리카노", americano.getName())
        assertThat(americano.getName()).isEqualTo("아메리카노") // 메서드 체이닝이 가능한 기법
    }

    @Test
    fun getPrice() {
        val americano = Americano()
        assertEquals(4000, americano.getPrice())
        assertThat(americano.getPrice()).isEqualTo(4000)
    }

    @Test
    @DisplayName("주문을 생성합니다.")
    fun createOrderWithCurrentTime() {
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        cafeKiosk.add(americano)

        val order = cafeKiosk.createOrder(LocalDateTime.of(2023, 5, 28, 10, 0))
        assertThat(order.beverages).hasSize(1)
        assertThat(order.beverages[0].getName()).isEqualTo(americano.getName())
    }

    @Test
    @DisplayName("주문을 생성합니다. - 예외테스트")
    fun createOrderOutsideOpenTime() {
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        cafeKiosk.add(americano)

        assertThatThrownBy { cafeKiosk.createOrder(LocalDateTime.of(2023, 5, 28, 9, 59)) }
                .isInstanceOf(IllegalStateException::class.java)
                .hasMessage("주문 가능 시간이 아닙니다.")
    }
}