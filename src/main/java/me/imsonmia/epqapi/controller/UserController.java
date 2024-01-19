package me.imsonmia.epqapi.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.imsonmia.epqapi.model.Message;
import me.imsonmia.epqapi.model.User;
import me.imsonmia.epqapi.repository.MessageRepository;
import me.imsonmia.epqapi.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    // request URL like .../user?id={number} or .../user?name={string}
    @GetMapping("/user")
    public ResponseEntity<User> getUserByParam(@RequestParam(value = "id") Optional<Long> id,
            @RequestParam(value = "name") Optional<String> name) {
        if (!id.isPresent()) {
            if (!name.isPresent()) {
                // malformed request
                return ResponseEntity.badRequest().build();
            } else {
                boolean exists = userRepository.existsByUserName(name.get());
                // Filter by name branch
                if (!exists) {
                    return ResponseEntity.notFound().build();
                } else {
                    return ResponseEntity.ok().body(userRepository.findByUserName(name.get()).get());
                }
            }
        } else {
            // get by id branch
            return ResponseEntity.ok().body(userRepository.findById(id.get()).get());
        }
    }

    @PostMapping("/user")
    public ResponseEntity<Optional<User>> addUser(
            @RequestBody User newUser) {
        if (userRepository.existsByUserName(newUser.getUserName())) {
            return ResponseEntity.badRequest().build();
        }
        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{id}")
    public boolean deleteUser(
            @PathVariable(value = "id") Long id) {
        // user doesn't exist
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    @GetMapping("/msg/{from}")
    public ArrayList<Message> getMessagesFromTimestamp(@PathVariable(value = "from") Long fromTimestamp) {
        if (fromTimestamp < 0) {
            return new ArrayList<>();
        }
        ;
        ArrayList<Message> messages = new ArrayList<>();
        Instant targetInstant = Instant.ofEpochMilli(fromTimestamp);
        for (Message msg : messageRepository.findAll()) {
            Instant t = Instant.ofEpochMilli(msg.getTimeMillis());
            if (t.isBefore(targetInstant)) {
                continue;
            }
            messages.add(msg);
        }
        return messages;
    }
}
