package me.imsonmia.epqapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.imsonmia.epqapi.model.User;
import me.imsonmia.epqapi.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return userRepository.findById(id).get(); 
    }
    @PostMapping("/user")
    public User addUser(
        @RequestBody
        User newUser
    ) {
        return userRepository.save(newUser);
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(
        @PathVariable(value = "id") Long id
    ) {
        userRepository.deleteById(id);
    }
}
