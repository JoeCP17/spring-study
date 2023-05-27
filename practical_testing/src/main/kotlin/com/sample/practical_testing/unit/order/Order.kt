package com.sample.practical_testing.unit.order

import com.sample.practical_testing.unit.beverage.Beverage
import java.time.LocalDateTime

data class Order(
        val orderDateTime: LocalDateTime,
        val beverages: List<Beverage>
) {

}