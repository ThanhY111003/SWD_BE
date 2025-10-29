package com.example.Back_end.repository;

import com.example.Back_end.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    // ✅ Lấy staff đầu tiên thuộc lab nhất định (thường là người quản lý lab)
    Optional<Staff> findFirstByLab_LabId(Long labId);

    // ✅ Lấy danh sách tất cả staff trong một lab
    List<Staff> findByLab_LabId(Long labId);

    // ✅ Lấy staff theo userId (nếu cần truy ngược từ tài khoản người dùng)
    Optional<Staff> findByUser_UserId(Long userId);
}
