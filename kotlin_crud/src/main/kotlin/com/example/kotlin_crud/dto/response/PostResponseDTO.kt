package com.example.kotlin_crud.dto.response

import com.example.kotlin_crud.entity.post.Post
import java.time.LocalDateTime

data class PostResponseDTO(
        val id: Long,
        val title: String,
        val content: String?,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
) {

    companion object {
        fun toDTO(savePost: Post): PostResponseDTO {
            return PostResponseDTO(
                    id = savePost.id!!,
                    title = savePost.title!!,
                    content = savePost.content,
                    createdAt = savePost.createdAt!!,
                    updatedAt = savePost.updatedAt!!
            )
        }
    }
}