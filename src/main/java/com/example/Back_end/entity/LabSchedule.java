package com.example.Back_end.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lab_schedule")
public class LabSchedule {

    @EmbeddedId
    private LabScheduleId id = new LabScheduleId();

    @ManyToOne
    @MapsId("scheduleId")
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @MapsId("labId")
    @JoinColumn(name = "lab_id")
    private Lab lab;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo; // nullable

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
