package com.example.Back_end.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/labs")
@Tag(name = "Lab Controller", description = "Quản lý phòng thí nghiệm (Lab)")
public class LabController {

    @Operation(
            summary = "Lấy tất cả lab",
            description = "Trả về danh sách tất cả phòng thí nghiệm"
    )
    @GetMapping
    public Object getAllLabs() {
        // TODO: Call service to get all labs
        return null;
    }

    @Operation(
            summary = "Lấy lab theo ID",
            description = "Trả về chi tiết phòng thí nghiệm dựa trên ID"
    )
    @GetMapping("/{id}")
    public Object getLabById(@PathVariable Integer id) {
        // TODO: Call service to get lab by id
        return null;
    }

    @Operation(
            summary = "Tạo mới lab",
            description = "Tạo một lab mới với thông tin cung cấp"
    )
    @PostMapping
    public Object createLab(@RequestBody Object labDto) {
        // TODO: Call service to create lab
        return null;
    }

    @Operation(
            summary = "Cập nhật lab theo ID",
            description = "Cập nhật thông tin phòng thí nghiệm dựa trên ID"
    )
    @PutMapping("/{id}")
    public Object updateLab(@PathVariable Integer id, @RequestBody Object labDto) {
        // TODO: Call service to update lab
        return null;
    }

    @Operation(
            summary = "Xóa lab theo ID",
            description = "Xóa phòng thí nghiệm khỏi hệ thống dựa trên ID"
    )
    @DeleteMapping("/{id}")
    public Object deleteLab(@PathVariable Integer id) {
        // TODO: Call service to delete lab
        return null;
    }

    @Operation(
            summary = "Cập nhật trạng thái lab",
            description = "Cập nhật trạng thái của lab (Available, InUse, Maintenance)"
    )
    @PatchMapping("/{id}/status")
    public Object updateLabStatus(@PathVariable Integer id, @RequestParam String status) {
        // TODO: Call service to update lab status
        return null;
    }
}
