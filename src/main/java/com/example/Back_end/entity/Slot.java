package com.example.Back_end.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "slot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id")
    private Integer slotId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @ManyToMany
    @JoinTable(
            name = "lab_slot",
            joinColumns = @JoinColumn(name = "slot_id"),
            inverseJoinColumns = @JoinColumn(name = "lab_id")
    )
    private List<Lab> labs;

}
