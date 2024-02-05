package com.example.restcrudmahdi

import com.example.restcrudmahdi.domain.Message
import com.example.restcrudmahdi.service.MessageService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest")
class MessageController constructor(
    private val messageService: MessageService
) {
    @PostMapping("/createMessage")
    fun createMessage(@RequestBody message: Message): String {
        return messageService.create(message);
    }

    @GetMapping("/getMessage/{message}")
    fun getMessage(@PathVariable(value = "message") message: String): List<Message>{
        return messageService.getMessage(message)
    }

    @DeleteMapping("/deleteMessage/{id}")
    fun deleteMessage(@PathVariable(value = "id") id: Long) {
        messageService.deleteById(id)
    }
}