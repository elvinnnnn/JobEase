package com.jobease.repository;

import org.springframework.data.repository.CrudRepository;

import com.jobease.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
