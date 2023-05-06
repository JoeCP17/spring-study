package com.example.kotlin_crud.entity.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository : JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p offset :page limit :size", nativeQuery = true)
    fun findAllByPageAndSize(page: Int, size: Int): List<Post>
}