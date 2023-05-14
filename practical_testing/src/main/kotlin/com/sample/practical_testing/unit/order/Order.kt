package com.sample.practical_testing.unit.order

import com.sample.practical_testing.unit.beverage.Beverage
import java.time.LocalDateTime

class Order(
        private val orderDateTime: LocalDateTime,
        private val beverages: List<Beverage>
) {

}