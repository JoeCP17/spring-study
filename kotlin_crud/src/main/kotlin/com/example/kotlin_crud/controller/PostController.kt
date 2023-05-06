package com.example.kotlin_crud.controller

import com.example.kotlin_crud.dto.request.PostRequestDTO
import com.example.kotlin_crud.dto.response.PostResponseDTO
import com.example.kotlin_crud.service.PostService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
class PostController(
        private val postService: PostService
) {

    @GetMapping("/find/all")
    fun findAllPosts(
            @RequestParam page: Int,
            @RequestParam size: Int
    ): List<PostResponseDTO> {
        return postService.findAllPosts(page, size)
    }

    @GetMapping("/find/{id}")
    fun findPostById(@PathVariable id: Long): PostResponseDTO {
        return postService.findPostById(id)
    }

    @PostMapping("/create")
    fun createPost(@RequestBody request: PostRequestDTO): PostResponseDTO {
        return postService.createPost(request)
    }

    @PutMapping("/update/{id}")
    fun updatePostById(
            @PathVariable id: Long,
            @RequestBody request: PostRequestDTO): PostResponseDTO {
        return postService.updatePostById(id, request)
    }

}