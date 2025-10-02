package com.example.Back_end.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role Controller", description = "Quản lý vai trò")
public class RoleController {

    @Operation(
            summary = "Lấy tất cả vai trò",
            description = "Trả về danh sách tất cả vai trò trong hệ thống"
    )
    @GetMapping
    public Object getAllRoles() {
        // TODO: Call service to get all roles
        return null;
    }

    @Operation(
            summary = "Lấy thông tin vai trò theo ID",
            description = "Trả về thông tin chi tiết của vai trò dựa trên ID"
    )
    @GetMapping("/{id}")
    public Object getRoleById(@PathVariable Integer id) {
        // TODO: Call service to get role by id
        return null;
    }

    @Operation(
            summary = "Tạo mới vai trò",
            description = "Tạo một vai trò mới với thông tin được cung cấp"
    )
    @PostMapping
    public Object createRole(@RequestBody Object roleDto) {
        // TODO: Call service to create role
        return null;
    }

    @Operation(
            summary = "Cập nhật vai trò theo ID",
            description = "Cập nhật thông tin vai trò dựa trên ID và dữ liệu mới"
    )
    @PutMapping("/{id}")
    public Object updateRole(@PathVariable Integer id, @RequestBody Object roleDto) {
        // TODO: Call service to update role
        return null;
    }

    @Operation(
            summary = "Xóa vai trò theo ID",
            description = "Xóa vai trò khỏi hệ thống dựa trên ID"
    )
    @DeleteMapping("/{id}")
    public Object deleteRole(@PathVariable Integer id) {
        // TODO: Call service to delete role
        return null;
    }
}
