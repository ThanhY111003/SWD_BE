package com.example.Back_end.controller;

import com.example.Back_end.entity.User;
import com.example.Back_end.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Lấy tất cả người dùng")
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Tạo mới người dùng")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Lấy thông tin người dùng theo ID")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return null; // tạm thời
    }

    @Operation(summary = "Cập nhật thông tin người dùng theo ID")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return null; // tạm thời
    }

    @Operation(summary = "Xóa người dùng theo ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // tạm thời không làm gì
    }

    @Operation(summary = "Tìm kiếm người dùng theo từ khóa")
    @GetMapping("/search")
    public List<User> search(@RequestParam String keyword) {
        return userService.searchUsers(keyword);
    }

    @Operation(summary = "Sắp xếp người dùng theo tên (tăng dần)")
    @GetMapping("/sort/asc")
    public List<User> sortAsc() {
        return userService.sortUsersByNameAsc();
    }

    @Operation(summary = "Sắp xếp người dùng theo tên (giảm dần)")
    @GetMapping("/sort/desc")
    public List<User> sortDesc() {
        return userService.sortUsersByNameDesc();
    }
}
