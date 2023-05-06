package com.example.kotlin_crud.dto.request

import com.example.kotlin_crud.entity.member.Member
import com.example.kotlin_crud.entity.post.Post

data class PostRequestDTO(
        val title: String,
        val content: String?,
        val memberId: Long
) {
    fun toEntity(member: Member, request: PostRequestDTO): Post {
        return Post(
                title = request.title,
                content = request.content,
                author = member
        )
    }

}
