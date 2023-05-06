package com.example.kotlin_crud.entity.member

import com.example.kotlin_crud.common.BaseEntity
import jakarta.persistence.Entity


@Entity
class Member(
        var name: String? = null,
        var password: String? = null
) : BaseEntity() {


    fun update(name: String, password: String) {
        this.name = name
        this.password = password
    }
}