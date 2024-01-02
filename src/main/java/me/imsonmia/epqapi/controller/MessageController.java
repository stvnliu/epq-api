package me.imsonmia.epqapi.controller;

import java.util.ArrayList;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import me.imsonmia.epqapi.model.Message;
import me.imsonmia.epqapi.repository.MessageRepository;

@Controller
// @RequestMapping("/api/v1")
public class MessageController {
    private MessageRepository repository;
    @MessageMapping("/chat")
    @SendTo("/sub/chat")
    public Message messageHandler(Message message) throws Exception {
        // Add message to repository
        repository.save(message);

        // Forward message to subscribers of Stomp endpoint
        return message;
    }
    @GetMapping("/chat/history/{from}")
    public ArrayList<Message> getMessagesFromTimestamp(@PathVariable(value = "from") Long fromTimestamp) {
        return new ArrayList<Message>();
    }
    // @GetMapping("/msg/{id}")
    // public ChatMessage getMessageById(@PathVariable(value = "id") Long id) {
    //     return chatMessageRepository.findById(id).get();
    // }
}
