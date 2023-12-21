package me.imsonmia.epqapi.model;

import me.imsonmia.epqapi.repository.UserRepository;

public class ChatConvert {
    private UserRepository r;
    public ChatMessage fromMessage(Message s) {
        return new ChatMessage(r.findByUserName(s.getFrom()).getId(), s.getContent());
    }
}
