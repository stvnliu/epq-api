package me.imsonmia.epqapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.imsonmia.epqapi.model.ChatMessage;
import me.imsonmia.epqapi.repository.ChatMessageRepository;

@RestController
@RequestMapping("/api/v1")
public class ChatMessageController {
    private ChatMessageRepository chatMessageRepository;
    @GetMapping("/msg/{id}")
    public ChatMessage getMessageById(@PathVariable(value = "id") Long id) {
        return chatMessageRepository.findById(id).get();
    }
}
