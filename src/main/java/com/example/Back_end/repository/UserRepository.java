package com.example.Back_end.repository;

import com.example.Back_end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{
    // search bằng username (LIKE)
    List<User> findByUsernameContainingIgnoreCase(String keyword);

    // filter by role
    List<User> findByRole(String role);
}
