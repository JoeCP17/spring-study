package com.sample.practical_testing.spring.domain.product

import com.sample.practical_testing.spring.domain.BaseEntity
import javax.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productNumber: String,

    @Enumerated(EnumType.STRING)
    val type: ProductType,

    @Enumerated(EnumType.STRING)
    val sellingStatus: ProductSellingStatus,

    val name: String,

    val price: Int,
) : BaseEntity() {



}