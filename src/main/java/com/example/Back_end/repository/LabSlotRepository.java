package com.example.Back_end.repository;

import com.example.Back_end.entity.LabSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabSlotRepository extends JpaRepository<LabSlot, Integer> {

    List<LabSlot> findByLabLabId(Integer labId);
}
