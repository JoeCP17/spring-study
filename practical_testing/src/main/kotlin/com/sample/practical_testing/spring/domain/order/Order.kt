package com.sample.practical_testing.spring.domain.order

import com.sample.practical_testing.spring.domain.BaseEntity
import com.sample.practical_testing.spring.domain.orderProduct.OrderProduct
import com.sample.practical_testing.spring.domain.product.Product
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    val orderStatus: OrderStatus,

    val totalPrice: Int,

    val registeredDateTime: LocalDateTime? = LocalDateTime.now(),

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    val orderProducts: MutableList<OrderProduct>

): BaseEntity() {


    companion object {
        fun create(products: List<Product>, registeredDateTime: LocalDateTime): Order {
            val order = Order(
                orderStatus = OrderStatus.INIT,
                totalPrice = calculateTotalPrice(products),
                registeredDateTime = registeredDateTime,
                orderProducts = mutableListOf()
            )
            val orderProducts = products.map { product -> OrderProduct.of(order, product) }
            order.orderProducts.addAll(orderProducts)

            return order
        }

        private fun calculateTotalPrice(products: List<Product>): Int {
            return products.sumOf { product -> product.price }
        }
    }
}