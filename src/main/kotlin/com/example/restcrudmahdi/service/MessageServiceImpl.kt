package com.example.restcrudmahdi.service

import com.example.restcrudmahdi.domain.Message
import com.example.restcrudmahdi.repo.MessageRepository
import org.hibernate.service.spi.ServiceException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.EntityNotFoundException
import javax.persistence.Query

@Suppress("UNREACHABLE_CODE")
@Service
class MessageServiceImpl @Autowired constructor(
    private val repository: MessageRepository,
    private val entityManager: EntityManager
) : MessageService {
    companion object {
        const val MESSAGE_NOT_FOUND = "There is no member found with id: "
    }

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun create(entity: Message): String {
        try {
            repository.save(entity)
            return "its ok :)"
        } catch (e: Exception) {
            log.error("Error persisting a new Message: {}", e.message, e)
            throw ServiceException("Error persisting a new Message", e)
        }


    }

    override fun update(id: Long, entity: Message): Message {
        try {
            val persistedEntity =
                repository.findById(id).orElseThrow { EntityNotFoundException(MESSAGE_NOT_FOUND + id) }
            updateFieleds(persistedEntity, entity)
            persistedEntity.id = id
            return repository.save(persistedEntity)
        } catch (e: Exception) {
            log.error("Error updating a Message: {}", e.message, e)
            throw ServiceException("Error updating a Message", e)
        }
    }

    override fun getMessage(message: String): List<Message> {
        try {
//            return repository.findByMessage(message) //write native query
            var sql: String = "SELECT m from Message m where m.message = :message"
            var query: Query = entityManager.createQuery(sql)
            query.setParameter("message", message)
            return query.resultList as List<Message>

        } catch (e: Exception) {
            log.error("Error getting")
            throw Exception("Error getting", e)
        }
    }


    override fun findById(id: Long): Message {
        return repository.findById(id).orElseThrow { EntityNotFoundException(MESSAGE_NOT_FOUND + id) }
    }

    override fun findAll(): List<Message> {
        try {
            val message = repository.findAll()
            return message
        } catch (e: Exception) {
            log.error("Error retrieving all existing messages: {}", e.message, e)
            throw ServiceException("Error retrieving all existing messages", e)
        }
    }

    override fun deleteById(id: Long) {
        repository.findById(id).orElseThrow { EntityNotFoundException(MESSAGE_NOT_FOUND + id) }
        try {
            repository.deleteById(id)
        } catch (e: DataIntegrityViolationException) {
            log.error("Error deleting Message with id: " + id + " - " + e.message, e)
        }
    }

    private fun updateFieleds(persistedEntity: Message, newEntity: Message) {
        persistedEntity.message = newEntity.message
    }
}