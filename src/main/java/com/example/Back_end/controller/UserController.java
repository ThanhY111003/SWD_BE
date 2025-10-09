package com.example.Back_end.controller;

import com.example.Back_end.dto.UserDTO;
import com.example.Back_end.entity.User;
import com.example.Back_end.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Quản lý người dùng")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ---------------- GET ALL ----------------
    @Operation(summary = "Lấy danh sách người dùng, hỗ trợ tìm kiếm và sắp xếp")
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "asc") String sort) {

        List<User> result;

        if (keyword != null && !keyword.isEmpty()) {
            result = userService.searchUsers(keyword);
        } else if ("desc".equalsIgnoreCase(sort)) {
            result = userService.sortUsersByNameDesc();
        } else {
            result = userService.sortUsersByNameAsc();
        }

        return ResponseEntity.ok(result);
    }

    // ---------------- GET BY ID ----------------
    @Operation(summary = "Lấy thông tin người dùng theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // ---------------- CREATE ----------------
    @Operation(
            summary = "Tạo mới người dùng",
            requestBody = @RequestBody(
                    description = "Dữ liệu người dùng cần tạo",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserDTO.class),
                            examples = {
                                    @ExampleObject(value = """
                    {
                        "userName": "johndoe",
                        "email": "john@example.com",
                        "status": "ACTIVE"
                    }
                    """)
                            }
                    )
            )
    )
    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @org.springframework.web.bind.annotation.RequestBody UserDTO user) {
        User created = userService.createUser(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // ---------------- UPDATE ----------------
    @Operation(summary = "Cập nhật thông tin người dùng theo ID")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Integer id,
            @Valid @org.springframework.web.bind.annotation.RequestBody User user) {
        user.setUserId(id);
        User updated = userService.updateUser(user);
        return ResponseEntity.ok(updated);
    }

    // ---------------- DELETE ----------------
    @Operation(summary = "Xóa người dùng theo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
