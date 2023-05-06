package com.example.kotlin_crud.dto.response

import com.example.kotlin_crud.entity.member.Member
import java.time.LocalDateTime

data class MemberResponseDTO(
        val id: Long,
        val name: String,
        val password: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
) {

    companion object {
        fun toDTO(entity: Member): MemberResponseDTO {
            return MemberResponseDTO(
                    id = entity.id!!,
                    name = entity.name!!,
                    password = entity.password!!,
                    createdAt = entity.createdAt!!,
                    updatedAt = entity.updatedAt!!
            )
        }
    }
}