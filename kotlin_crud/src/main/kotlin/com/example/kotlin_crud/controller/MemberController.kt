package com.example.kotlin_crud.controller

import com.example.kotlin_crud.dto.request.MemberRequestDTO
import com.example.kotlin_crud.dto.response.MemberResponseDTO
import com.example.kotlin_crud.service.MemberService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/member")
class MemberController(
        private val memberService: MemberService
) {

    @PostMapping("/create")
    fun createMember(
            @RequestBody request: MemberRequestDTO): MemberResponseDTO {
        return memberService.createMember(request)
    }

    @GetMapping("find/all")
    fun findAllMember(
            @RequestParam(required = false, defaultValue = "1") page: Int,
            @RequestParam(required = false, defaultValue = "10") size: Int
    ): List<MemberResponseDTO> {
        return memberService.findAllMember(page, size)
    }

    @GetMapping("/find/{id}")
    fun findMemberById(
            @PathVariable id: Long): MemberResponseDTO {
        return memberService.findMemberById(id)
    }

    @PutMapping("/update/{id}")
    fun updateMember(
            @PathVariable id: Long,
            @RequestBody request: MemberRequestDTO): MemberResponseDTO {
        return memberService.updateMemberById(id, request)
    }

}