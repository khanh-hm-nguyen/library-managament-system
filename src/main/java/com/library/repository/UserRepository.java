package com.library.repository;

import com.library.model.User;

import java.util.List;

public interface UserRepository {
    void save (User user);
    User findById(String id);
    List<User> findAll();
    void delete(String id);
}
