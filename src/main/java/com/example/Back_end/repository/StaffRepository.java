package com.example.Back_end.repository;

import com.example.Back_end.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    // Lấy danh sách staff theo labId
    @Query("SELECT s FROM Staff s JOIN s.labs l WHERE l.labId = :labId")
    List<Staff> findByLabId(@Param("labId") Long labId);

    // Lấy staff đầu tiên theo labId
    @Query("SELECT s FROM Staff s JOIN s.labs l WHERE l.labId = :labId")
    Optional<Staff> findFirstByLabId(@Param("labId") Long labId);

    boolean existsByUser_UserId(Long userId);

}
