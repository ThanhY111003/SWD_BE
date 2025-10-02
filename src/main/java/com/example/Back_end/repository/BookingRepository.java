package com.example.Back_end.repository;

import com.example.Back_end.entity.Booking;
import com.example.Back_end.entity.entity_enum.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByUserUserId(Integer userId);

    List<Booking> findByLabLabId(Integer labId);

    List<Booking> findByStatus(BookingStatus status);
}
