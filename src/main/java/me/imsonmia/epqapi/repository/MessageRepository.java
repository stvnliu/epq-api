package me.imsonmia.epqapi.repository;

import org.springframework.data.repository.CrudRepository;
import me.imsonmia.epqapi.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
