package me.imsonmia.epqapi.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class Message {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long    id;
    String  fromUserId;
    String  toUserId;
    String  content;
    Long    timeMillis;
    public Long getId() {
        return id;
    }
    public String getFromUserId() {
        return fromUserId;
    }
    public String getToUserId() {
        return toUserId;
    }
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getTimeMillis() {
        return timeMillis;
    }
    public void setTimeMillis(Long timeMillis) {
        this.timeMillis = timeMillis;
    }
    public Message() {

    }
    public Message(Long id,
    String  fromUserId,
    String  toUserId,
    String  content,
    Long    timeMillis) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.content = content;
        this.timeMillis = timeMillis;
    }
}
