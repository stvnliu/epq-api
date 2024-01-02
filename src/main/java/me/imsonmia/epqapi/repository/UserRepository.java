package me.imsonmia.epqapi.repository;

import org.springframework.data.repository.CrudRepository;

import me.imsonmia.epqapi.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
// NOTE timestamp is a reserved SQL keyword
// create table user (date_joined datetime(6), id bigint not null, user_name varchar(255), primary key (id)) engine=InnoDB
// create table message (id bigint not null, timestamp bigint, content varchar(255), from varchar(255), to varchar(255), primary key (id)) engine=InnoDB