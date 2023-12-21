package me.imsonmia.epqapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long        id;
    @Getter
    @Setter
    Long        fromId;
    @Getter
    @Setter
    String      text;
    @Getter
    @Setter
    String[]    attachments;
    public ChatMessage() {}
    public ChatMessage(
        Long fromId,
        String text
        ) {
        this.fromId = fromId;
        this.text = text;
    }
    
}