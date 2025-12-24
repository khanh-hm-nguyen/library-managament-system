package com.library.repository;

import com.library.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> userDB = new HashMap<>();

    @Override
    public void save(User user) {
        userDB.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return userDB.get(id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userDB.values());
    }

    @Override
    public void delete(String id) {
        userDB.remove(id);
    }
}
