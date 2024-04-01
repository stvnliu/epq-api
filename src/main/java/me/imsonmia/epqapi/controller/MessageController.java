package me.imsonmia.epqapi.controller;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import me.imsonmia.epqapi.model.Message;
import me.imsonmia.epqapi.repository.MessageRepository;

@Controller
// @RequestMapping("/api/v1")
public class MessageController {
    @Autowired
    private MessageRepository repository;

    @MessageMapping("/chat")
    @SendTo("/sub/chat")
    public Message messageHandler(Message message) throws Exception {
        // Add message to repository
        repository.save(message);
        // Forward message to subscribers of Stomp endpoint
        return message;
    }

    // @GetMapping("/msg/{id}")
    // public ChatMessage getMessageById(@PathVariable(value = "id") Long id) {
    // return chatMessageRepository.findById(id).get();
    // }
}
