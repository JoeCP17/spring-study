package com.sample.practical_testing.spring.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @CreatedDate
    var createdAt: LocalDateTime? = LocalDateTime.now()

    @LastModifiedDate
    var updatedAt: LocalDateTime? = LocalDateTime.now()

}