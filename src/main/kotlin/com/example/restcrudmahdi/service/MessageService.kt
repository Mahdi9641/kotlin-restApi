package com.example.restcrudmahdi.service

import com.example.restcrudmahdi.domain.Message
import javax.persistence.EntityNotFoundException
import kotlin.jvm.Throws

interface MessageService: BaseService<Message> {

    fun getMessage(entity: String): List<Message>
}