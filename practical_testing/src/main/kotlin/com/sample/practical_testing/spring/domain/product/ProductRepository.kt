package com.sample.practical_testing.spring.domain.product

import org.springframework.data.jpa.repository.JpaRepository


interface ProductRepository: JpaRepository<Product, Long> {

    /**
     * select *
     * from product
     * where selling_status in ('SELLING', 'HOLD')
     */
    fun findAllBySellingStatusIn(sellingStatuses: List<ProductSellingStatus>): List<Product>

}