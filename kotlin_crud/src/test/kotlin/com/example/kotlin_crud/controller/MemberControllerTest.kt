package com.example.kotlin_crud.controller

import com.example.kotlin_crud.dto.request.MemberRequestDTO
import com.example.kotlin_crud.dto.response.MemberResponseDTO
import com.example.kotlin_crud.service.MemberService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@SpringBootTest
@DisplayName("Member Controller 테스트")
internal class MemberControllerTest {

    val mockMvc: MockMvc? = null

    private val objectMapper : ObjectMapper = ObjectMapper()

    @MockBean
    private val memberService: MemberService? = null

    lateinit var memberRequestDTO: MemberRequestDTO

    lateinit var memberResponseDTO: MemberResponseDTO

    @BeforeEach
    fun setUp() {
        memberRequestDTO = MemberRequestDTO(
                name = "test",
                password = "test123"
        )

        memberResponseDTO = MemberResponseDTO(
                id = 1L,
                name = "test",
                password = "test123",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
        )
    }

    @Test
    @DisplayName("회원 생성 API 테스트")
    fun createMember() {
        //given
        given(memberService?.createMember(memberRequestDTO)).willReturn(memberResponseDTO)

        //when & then
        mockMvc?.perform(post("/api/member/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberRequestDTO)))
                ?.andExpect(status().isOk)
                ?.andExpect(jsonPath("$.id").value(1L))
                ?.andExpect(jsonPath("$.name").value("test"))
                ?.andExpect(jsonPath("$.password").value("test123"))
                ?.andExpect(jsonPath("$.createdAt").exists())
                ?.andExpect(jsonPath("$.updatedAt").exists())
    }

    @Test
    @DisplayName("회원 전체 조회 API 테스트")
    fun findAllMember() {
        // given
        given(memberService?.findAllMember(1, 10)).willReturn(listOf(memberResponseDTO))

        // when & then
        mockMvc?.perform(post("/api/member/find/all")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberRequestDTO)))
                ?.andExpect(status().isOk)
                ?.andExpect(jsonPath("$[0].id").value(1L))
                ?.andExpect(jsonPath("$[0].name").value("test"))
                ?.andExpect(jsonPath("$[0].password").value("test123"))
                ?.andExpect(jsonPath("$[0].createdAt").exists())
                ?.andExpect(jsonPath("$[0].updatedAt").exists())
    }

    @Test
    @DisplayName("회원 단건 조회 API 테스트")
    fun findMemberById() {
        // given
        given(memberService?.findMemberById(1L)).willReturn(memberResponseDTO)

        // when & then
        mockMvc?.perform(post("/api/member/find/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberRequestDTO)))
                ?.andExpect(status().isOk)
                ?.andExpect(jsonPath("$.id").value(1L))
                ?.andExpect(jsonPath("$.name").value("test"))
                ?.andExpect(jsonPath("$.password").value("test123"))
                ?.andExpect(jsonPath("$.createdAt").exists())
                ?.andExpect(jsonPath("$.updatedAt").exists())
    }

    @Test
    @DisplayName("회원 수정 API 테스트")
    fun updateMember() {
        // given
        given(memberService?.updateMemberById(1L, memberRequestDTO)).willReturn(memberResponseDTO)

        // when & then
        mockMvc?.perform(post("/api/member/update/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberRequestDTO)))
                ?.andExpect(status().isOk)
                ?.andExpect(jsonPath("$.id").value(1L))
                ?.andExpect(jsonPath("$.name").value("test"))
                ?.andExpect(jsonPath("$.password").value("test123"))
                ?.andExpect(jsonPath("$.createdAt").exists())
                ?.andExpect(jsonPath("$.updatedAt").exists())
    }
}