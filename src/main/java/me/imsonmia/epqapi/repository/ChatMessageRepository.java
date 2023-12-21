package me.imsonmia.epqapi.repository;

import org.springframework.data.repository.CrudRepository;

import me.imsonmia.epqapi.model.ChatMessage;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long> {
}
