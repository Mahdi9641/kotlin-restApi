package com.example.restcrudmahdi.domain

import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, insertable = false)
    var id: Long? = null

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null

    @PrePersist
    protected  fun prePersist(){
        if(createdAt == null) createdAt = LocalDateTime.now()
    }
}