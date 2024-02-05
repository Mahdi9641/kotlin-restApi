package com.example.restcrudmahdi

import com.example.restcrudmahdi.domain.Message
import com.example.restcrudmahdi.domain.MessageDao
import com.example.restcrudmahdi.service.MessageService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/rest")
class MessageController constructor(
    private val messageService: MessageService
) {
    @PostMapping("/createMessage")
    fun createMessage(@RequestBody messageReq: MessageDao): String {
        var message: Message = Message()
        message.createdAt = messageReq.createdAt
        message.message = messageReq.message
        message.pic = Base64.getDecoder().decode(messageReq.pic)

        return messageService.create(message)
    }

    @GetMapping("/getMessage/{message}")
    fun getMessage(@PathVariable(value = "message") message: String): List<Message> {
        return messageService.getMessage(message)
    }

    @DeleteMapping("/deleteMessage/{id}")
    fun deleteMessage(@PathVariable(value = "id") id: Long) {
        messageService.deleteById(id)
    }
}