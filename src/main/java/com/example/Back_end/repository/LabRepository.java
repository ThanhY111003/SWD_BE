package com.example.Back_end.repository;

import com.example.Back_end.entity.Lab;
import com.example.Back_end.entity.entity_enum.LabStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab, Integer> {

    List<Lab> findByStatus(LabStatus status);

    List<Lab> findByLabNameContaining(String keyword);
}
