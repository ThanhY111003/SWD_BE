package com.example.Back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "room_slots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomSlotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id")
    private Slot slot;

    private LocalDate bookingDate;
    private String status;
    private Boolean isAvailable;

    @ManyToMany(mappedBy = "roomSlots")
    private List<Lab> labs;


}

