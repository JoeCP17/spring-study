package com.sample.practical_testing.practice.unit.beverage

class Latte : Beverage {
    override fun getName(): String {
        return "라떼"
    }

    override fun getPrice(): Int {
        return 4500
    }
}