package com.sample.practical_testing.spring.domain.stock

import com.sample.practical_testing.spring.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Stock(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productNumber: String,
    var quantity: Int,
) : BaseEntity() {
    fun isQuantityLessThan(quantity: Int): Boolean {
        return this.quantity < quantity
    }

    fun decreaseQuantity(quantity: Int) {
        if (isQuantityLessThan(quantity)) throw IllegalArgumentException("재고가 부족합니다.")
        this.quantity -= quantity
    }

    companion object {
        fun create(productNumber: String, quantity: Int): Stock {
            return Stock(productNumber = productNumber, quantity = quantity)
        }
    }
}
