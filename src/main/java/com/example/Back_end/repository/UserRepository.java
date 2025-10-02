package com.example.Back_end.repository;

import com.example.Back_end.entity.User;
import com.example.Back_end.entity.entity_enum.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    List<User> findByStatus(UserStatus status);
}
