package me.imsonmia.epqapi.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import me.imsonmia.epqapi.model.Message;

@Controller
// @RequestMapping("/api/v1")
public class ChatMessageController {
    @MessageMapping("/chat")
    @SendTo("/sub/chat")
    public Message messageHandler(Message message) throws Exception {
        return message;
    }
    // @GetMapping("/msg/{id}")
    // public ChatMessage getMessageById(@PathVariable(value = "id") Long id) {
    //     return chatMessageRepository.findById(id).get();
    // }
}
