package com.example.Back_end.controller;

import com.example.Back_end.entity.User;
import com.example.Back_end.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Quản lý người dùng")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/search")
    public List<User> search(@RequestParam String keyword) {
        return userService.searchUsers(keyword);
    }

    @GetMapping("/sort/asc")
    public List<User> sortAsc() {
        return userService.sortUsersByNameAsc();
    }

    @GetMapping("/sort/desc")
    public List<User> sortDesc() {
        return userService.sortUsersByNameDesc();
    }
}
