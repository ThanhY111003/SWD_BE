package com.example.Back_end.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security-events")
@Tag(name = "Security Event Controller", description = "Quản lý sự kiện an ninh (SecurityEvent)")
public class SecurityEventController {

    @Operation(
            summary = "Lấy tất cả sự kiện an ninh",
            description = "Trả về danh sách tất cả sự kiện an ninh trong hệ thống"
    )
    @GetMapping
    public Object getAllSecurityEvents() {
        // TODO: Call service to get all security events
        return null;
    }

    @Operation(
            summary = "Lấy sự kiện an ninh theo ID",
            description = "Trả về chi tiết sự kiện an ninh dựa trên ID"
    )
    @GetMapping("/{id}")
    public Object getSecurityEventById(@PathVariable Integer id) {
        // TODO: Call service to get security event by id
        return null;
    }

    @Operation(
            summary = "Lấy tất cả sự kiện an ninh của một user",
            description = "Trả về danh sách sự kiện an ninh theo userId"
    )
    @GetMapping("/user/{userId}")
    public Object getSecurityEventsByUser(@PathVariable Integer userId) {
        // TODO: Call service to get security events by user
        return null;
    }

    @Operation(
            summary = "Tạo mới sự kiện an ninh",
            description = "Tạo một sự kiện an ninh mới với thông tin cung cấp"
    )
    @PostMapping
    public Object createSecurityEvent(@RequestBody Object securityEventDto) {
        // TODO: Call service to create security event
        return null;
    }

    @Operation(
            summary = "Cập nhật sự kiện an ninh theo ID",
            description = "Cập nhật thông tin sự kiện an ninh dựa trên ID"
    )
    @PutMapping("/{id}")
    public Object updateSecurityEvent(@PathVariable Integer id, @RequestBody Object securityEventDto) {
        // TODO: Call service to update security event
        return null;
    }

    @Operation(
            summary = "Xóa sự kiện an ninh theo ID",
            description = "Xóa sự kiện an ninh khỏi hệ thống dựa trên ID"
    )
    @DeleteMapping("/{id}")
    public Object deleteSecurityEvent(@PathVariable Integer id) {
        // TODO: Call service to delete security event
        return null;
    }
}
