package com.testing;

import org.springframework.data.repository.CrudRepository;

import java.util.Map;

public interface UserRepository {
    void saveUser(User user);
    Map<String,User> findAllUsers();
    User findById(String id);
    void update(User user);
    void delete(String id);
}
