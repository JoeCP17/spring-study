package com.example.kotlin_crud.service

import com.example.kotlin_crud.dto.request.PostRequestDTO
import com.example.kotlin_crud.dto.response.PostResponseDTO
import com.example.kotlin_crud.entity.member.Member
import com.example.kotlin_crud.entity.post.Post
import com.example.kotlin_crud.entity.post.PostRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class PostService(
        private val memberService: MemberService,
        private val postRepository: PostRepository
) {

    fun findAllPosts(page: Int, size: Int): List<PostResponseDTO> {
        return postRepository.findAllByPageAndSize(page, size).map {
            PostResponseDTO.toDTO(it)
        }
    }

    fun findPostById(id: Long): PostResponseDTO {
        findPostBy(id).let {
            return PostResponseDTO.toDTO(it)
        }
    }

    @Transactional
    fun createPost(request: PostRequestDTO): PostResponseDTO {
        findMemberBy(request.memberId).let { member ->
            val savePost = postRepository.save(
                    request.toEntity(member, request)
            )
            return PostResponseDTO.toDTO(savePost)
        }
    }

    @Transactional
    fun updatePostById(id: Long, request: PostRequestDTO): PostResponseDTO {
        val postEntity = findPostBy(id)
        val memberEntity = findMemberBy(request.memberId)

        if(equalPostCreaterAndMember(postEntity.author?.id, memberEntity.id)) {
            updatePost(postEntity, request).let {
                return it
            }
        }
        throw IllegalArgumentException("게시글 작성자가 아닙니다.")
    }

    private fun updatePost(post: Post, request: PostRequestDTO): PostResponseDTO {
            post.update(
                    title = request.title,
                    content = request.content
            )
            return PostResponseDTO.toDTO(post)
    }

    private fun findMemberBy(id: Long) : Member {
        return memberService.findMemberEntityBy(id)
    }

    private fun equalPostCreaterAndMember(postCreaterId: Long?, requestMemberId: Long?): Boolean {
        return postCreaterId == requestMemberId
    }

    private fun findPostBy(id: Long): Post {
        return postRepository.findById(id).orElseThrow {
            throw IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        }
    }

}