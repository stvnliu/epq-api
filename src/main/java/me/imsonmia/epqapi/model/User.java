package me.imsonmia.epqapi.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long id;
    @Getter
    @Setter
    String userId;
    @Getter
    @Setter
    String userName;
    @Getter
    @Setter
    Date dateJoined;
    @Getter
    @Setter
    Date lastSeen;
    @Getter
    @Setter
    String passwordHash;

    public User() {

    }

    public User(String userId, String userName, Date dateJoined, Date lastSeen, String passwordHash) {
        this.userId = userId;
        this.userName = userName;
        this.dateJoined = dateJoined;
        this.lastSeen = lastSeen;
        this.passwordHash = passwordHash;
    }
}
