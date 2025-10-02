package com.example.Back_end.repository;

import com.example.Back_end.entity.RolePermission;
import com.example.Back_end.entity.RolePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {

    List<RolePermission> findByRoleRoleId(Integer roleId);

    List<RolePermission> findByPermissionPermissionId(Integer permissionId);
}
