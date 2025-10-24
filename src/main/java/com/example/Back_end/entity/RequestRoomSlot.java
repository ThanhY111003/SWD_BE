package com.example.Back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_room_slots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoomSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestRoomSlotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_slot_id")
    private RoomSlot roomSlot;

    private LocalDateTime bookedAt;
    private String status;
    @Column(columnDefinition = "TEXT")
    private String note;
}

