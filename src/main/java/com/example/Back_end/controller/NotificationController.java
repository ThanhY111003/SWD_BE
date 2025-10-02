package com.example.Back_end.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification Controller", description = "Quản lý thông báo (Notification)")
public class NotificationController {

    @Operation(
            summary = "Lấy tất cả thông báo",
            description = "Trả về danh sách tất cả thông báo trong hệ thống"
    )
    @GetMapping
    public Object getAllNotifications() {
        // TODO: Call service to get all notifications
        return null;
    }

    @Operation(
            summary = "Lấy thông báo theo ID",
            description = "Trả về chi tiết thông báo dựa trên ID"
    )
    @GetMapping("/{id}")
    public Object getNotificationById(@PathVariable Integer id) {
        // TODO: Call service to get notification by id
        return null;
    }

    @Operation(
            summary = "Lấy tất cả thông báo của user",
            description = "Trả về danh sách thông báo của một user theo userId"
    )
    @GetMapping("/user/{userId}")
    public Object getNotificationsByUser(@PathVariable Integer userId) {
        // TODO: Call service to get notifications by user
        return null;
    }

    @Operation(
            summary = "Tạo mới thông báo",
            description = "Tạo một thông báo mới cho user với thông tin cung cấp"
    )
    @PostMapping
    public Object createNotification(@RequestBody Object notificationDto) {
        // TODO: Call service to create notification
        return null;
    }

    @Operation(
            summary = "Đánh dấu thông báo là đã đọc",
            description = "Cập nhật trạng thái của thông báo thành READ"
    )
    @PatchMapping("/{id}/read")
    public Object markAsRead(@PathVariable Integer id) {
        // TODO: Call service to mark notification as read
        return null;
    }

    @Operation(
            summary = "Xóa thông báo theo ID",
            description = "Xóa thông báo khỏi hệ thống dựa trên ID"
    )
    @DeleteMapping("/{id}")
    public Object deleteNotification(@PathVariable Integer id) {
        // TODO: Call service to delete notification
        return null;
    }
}
