package me.imsonmia.epqapi.controller;

import java.time.Instant;
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
    @GetMapping("/api/v1/chat/history/{from}")
    public ArrayList<Message> getMessagesFromTimestamp(@PathVariable(value = "from") long fromTimestamp) {
        ArrayList<Message> messages = new ArrayList<>();
        Instant targetInstant = Instant.ofEpochMilli(fromTimestamp);
        for (Message msg : repository.findAll()) {
            Instant t = Instant.ofEpochMilli(msg.getTimestamp());
            if (t.isBefore(targetInstant)) {continue;}
            messages.add(msg);
        }
        return messages;
    }
    // @GetMapping("/msg/{id}")
    // public ChatMessage getMessageById(@PathVariable(value = "id") Long id) {
    //     return chatMessageRepository.findById(id).get();
    // }
}
