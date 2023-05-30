package com.sample.practical_testing.spring.domain.orderProduct

import com.sample.practical_testing.spring.domain.BaseEntity
import com.sample.practical_testing.spring.domain.order.Order
import com.sample.practical_testing.spring.domain.product.Product
import javax.persistence.*

@Entity
data class OrderProduct(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: Order,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: Product
): BaseEntity() {

        companion object {
            fun of(order: Order, product: Product): OrderProduct {
                return OrderProduct(
                    order = order,
                    product = product
                )
            }
        }
}
