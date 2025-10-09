package com.example.Back_end.entity;

import com.example.Back_end.entity.entity_enum.ActiveDays;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleID;

    private String scheduleName;
    private String description;

    @Enumerated(EnumType.STRING)
    private ActiveDays activeDays; // Monday–Sunday

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo; // nullable

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Slot> slots = new ArrayList<>();

    @ManyToMany(mappedBy = "schedules")
    private List<Lab> labs;

}
