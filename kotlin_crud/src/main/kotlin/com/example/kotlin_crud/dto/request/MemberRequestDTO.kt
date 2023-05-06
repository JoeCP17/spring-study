package com.example.kotlin_crud.dto.request

import com.example.kotlin_crud.entity.member.Member

data class MemberRequestDTO(
        val name: String,
        val password: String
) {

    fun toEntity(requestDTO: MemberRequestDTO): Member {
        return Member(
                name = requestDTO.name,
                password = requestDTO.password
        )
    }
}
