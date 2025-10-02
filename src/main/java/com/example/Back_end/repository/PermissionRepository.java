package com.example.Back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Back_end.entity.Permission;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Optional<Permission> findByPermissionName(String permissionName);
}
