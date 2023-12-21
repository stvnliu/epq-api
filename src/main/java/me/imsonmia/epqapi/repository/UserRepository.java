package me.imsonmia.epqapi.repository;

import org.springframework.data.repository.CrudRepository;

import me.imsonmia.epqapi.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
