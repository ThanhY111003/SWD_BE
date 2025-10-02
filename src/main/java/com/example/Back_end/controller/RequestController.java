package com.example.Back_end.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@Tag(name = "Request Controller", description = "Quản lý các yêu cầu (Request)")
public class RequestController {

    @Operation(
            summary = "Lấy tất cả yêu cầu",
            description = "Trả về danh sách tất cả yêu cầu trong hệ thống"
    )
    @GetMapping
    public Object getAllRequests() {
        // TODO: Call service to get all requests
        return null;
    }

    @Operation(
            summary = "Lấy thông tin yêu cầu theo ID",
            description = "Trả về thông tin chi tiết của yêu cầu dựa trên ID"
    )
    @GetMapping("/{id}")
    public Object getRequestById(@PathVariable Integer id) {
        // TODO: Call service to get request by id
        return null;
    }

    @Operation(
            summary = "Tạo mới yêu cầu",
            description = "Tạo một yêu cầu mới với thông tin được cung cấp"
    )
    @PostMapping
    public Object createRequest(@RequestBody Object requestDto) {
        // TODO: Call service to create request
        return null;
    }

    @Operation(
            summary = "Cập nhật yêu cầu theo ID",
            description = "Cập nhật thông tin yêu cầu dựa trên ID và dữ liệu mới"
    )
    @PutMapping("/{id}")
    public Object updateRequest(@PathVariable Integer id, @RequestBody Object requestDto) {
        // TODO: Call service to update request
        return null;
    }

    @Operation(
            summary = "Xóa yêu cầu theo ID",
            description = "Xóa yêu cầu khỏi hệ thống dựa trên ID"
    )
    @DeleteMapping("/{id}")
    public Object deleteRequest(@PathVariable Integer id) {
        // TODO: Call service to delete request
        return null;
    }

    @Operation(
            summary = "Cập nhật trạng thái yêu cầu",
            description = "Cập nhật trạng thái của yêu cầu (Pending, Processing, Completed, Rejected)"
    )
    @PatchMapping("/{id}/status")
    public Object updateRequestStatus(@PathVariable Integer id, @RequestParam String status) {
        // TODO: Call service to update request status
        return null;
    }
}
