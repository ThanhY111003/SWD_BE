package com.example.Back_end.service;

import com.example.Back_end.entity.User;
import com.example.Back_end.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> searchUsers(String keyword) {
        return userRepository.findByUserNameContainingIgnoreCase(keyword);
    }

    public List<User> sortUsersByNameAsc() {
        return userRepository.findAll(Sort.by("username").ascending());
    }

    public List<User> sortUsersByNameDesc() {
        return userRepository.findAll(Sort.by("username").descending());
    }
}
