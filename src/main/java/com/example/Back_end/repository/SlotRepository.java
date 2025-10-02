package com.example.Back_end.repository;

import com.example.Back_end.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Integer> {


    List<Slot> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
