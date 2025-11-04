package com.example.Back_end.repository;

import com.example.Back_end.entity.Lab;
import com.example.Back_end.entity.LabImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabImageRepository extends JpaRepository<LabImage, Long> {
    List<LabImage> findByLab(Lab lab);
}

