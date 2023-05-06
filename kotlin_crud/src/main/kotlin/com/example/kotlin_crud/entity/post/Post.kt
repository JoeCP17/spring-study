package com.example.kotlin_crud.entity.post

import com.example.kotlin_crud.common.BaseEntity
import com.example.kotlin_crud.entity.member.Member
import jakarta.persistence.*


@Entity
class Post(
        var title: String? = null,
        var content: String? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "author_id")
        val author : Member? = null
): BaseEntity() {

        fun update(title: String, content: String?) {
                this.title = title
                this.content = content
        }


}