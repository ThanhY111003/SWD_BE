package com.example.Back_end.repository;

import com.example.Back_end.entity.Supporter;
import com.example.Back_end.entity.SupporterShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface SupporterShiftRepository extends JpaRepository<SupporterShift, Long> {

    @Query("""
        SELECT DISTINCT ss FROM SupporterShift ss
        WHERE ss.shiftDate = :date
          AND ss.startTime <= :slotStart
          AND ss.endTime >= :slotEnd
    """)
    List<SupporterShift> findAvailableShiftsForSlot(
            @Param("date") LocalDate date,
            @Param("slotStart") LocalTime slotStart,
            @Param("slotEnd") LocalTime slotEnd
    );

}
