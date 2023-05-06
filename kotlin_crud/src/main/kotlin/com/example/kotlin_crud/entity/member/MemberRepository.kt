package com.example.kotlin_crud.entity.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemberRepository : JpaRepository<Member, Long> {

    fun findOneByName(name: String): Member?

    @Query(value = "SELECT * FROM member OFFSET :page LIMIT :size ", nativeQuery = true)
    fun findAllByPageAndSize(page: Int, size: Int): List<Member>
}


