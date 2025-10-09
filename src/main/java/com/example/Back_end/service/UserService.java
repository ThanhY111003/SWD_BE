package com.example.Back_end.service;

import com.example.Back_end.dto.UserDTO;
import com.example.Back_end.entity.User;
import com.example.Back_end.entity.entity_enum.UserStatus;
import com.example.Back_end.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Lấy tất cả user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Tạo mới user (từ DTO)
    public User createUser(UserDTO dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setStatus(
                dto.getStatus() != null
                        ? UserStatus.valueOf(dto.getStatus().toUpperCase())
                        : UserStatus.INACTIVE
        );
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    // ✅ Tìm kiếm theo username (case-insensitive)
    public List<User> searchUsers(String keyword) {
        return userRepository.findByUserNameContainingIgnoreCase(keyword);
    }

    // ✅ Sắp xếp tăng dần theo tên
    public List<User> sortUsersByNameAsc() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "userName"));
    }

    // ✅ Sắp xếp giảm dần theo tên
    public List<User> sortUsersByNameDesc() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "userName"));
    }

    // ✅ Lấy theo ID
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // ✅ Cập nhật thông tin user
    public User updateUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // ✅ Xoá user
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
