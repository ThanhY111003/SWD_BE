package com.example.Back_end.entity;

import com.example.Back_end.entity.entity_enum.ShiftType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "supportershift")
public class SupporterShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shiftId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShiftType shiftType;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate shiftDate;

    // Mỗi shift thuộc về 1 user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
