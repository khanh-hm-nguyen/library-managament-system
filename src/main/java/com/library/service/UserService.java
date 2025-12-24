package com.library.service;

import com.library.model.User;
import com.library.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String id, String name) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be empty");
        }

        User newUser = new User(id, name);
        userRepository.save(newUser);
    }

    public List <User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> searchUsers(String query) {
        List<User> results = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            if (user.getName().toLowerCase().contains(query) ||
                    user.getId().toLowerCase().contains(query)) {
                results.add(user);
            }
        }

        return results;
    }
}
