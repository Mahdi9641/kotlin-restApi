package com.example.restcrudmahdi.repo

import com.example.restcrudmahdi.domain.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message , Long> {
    fun findByMessage(message: String?) : Message
}