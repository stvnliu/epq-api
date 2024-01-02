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
    Long    id;
    @Getter
    @Setter
    String  userName;
    @Getter
    @Setter
    Date    dateJoined;
    public User() {

    }
    public User(Long id, String userName, Date dateJoined) {
        this.id = id;
        this.userName = userName;
        this.dateJoined = dateJoined;
    }
}
