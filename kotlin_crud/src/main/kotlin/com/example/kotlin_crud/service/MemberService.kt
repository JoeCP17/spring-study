package com.example.kotlin_crud.service

import com.example.kotlin_crud.dto.request.MemberRequestDTO
import com.example.kotlin_crud.dto.response.MemberResponseDTO
import com.example.kotlin_crud.entity.member.Member
import com.example.kotlin_crud.entity.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
        private val memberRepository: MemberRepository
) {

    fun findMemberById(id: Long): MemberResponseDTO {
        findMemberEntityBy(id).let {
            return MemberResponseDTO.toDTO(it)
        }
    }

    fun findAllMember(page: Int, size: Int): List<MemberResponseDTO> {
        return memberRepository.findAllByPageAndSize(page, size).map {
            MemberResponseDTO.toDTO(it)
        }
    }

    @Transactional
    fun createMember(request: MemberRequestDTO): MemberResponseDTO {
        findDuplicateMemberByName(request.name)

        val saveMember = memberRepository.save(
                request.toEntity(request)
        )

        return MemberResponseDTO.toDTO(saveMember)
    }

    @Transactional
    fun updateMemberById(id: Long, request: MemberRequestDTO): MemberResponseDTO {
        val member = findMemberEntityBy(id)

        member.update(
                name = request.name,
                password = request.password
        )

        return MemberResponseDTO.toDTO(member)
    }

        private fun findDuplicateMemberByName(name: String) {
        val findMember = memberRepository.findOneByName(name)

        if(findMember != null) {
            throw IllegalArgumentException("이미 존재하는 유저 입니다.")
        }
    }

     fun findMemberEntityBy(id: Long): Member {
        return memberRepository.findById(id).orElseThrow {
            throw IllegalArgumentException("현재 멤버가 존재하지 않습니다.")
        }
    }
}