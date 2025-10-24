package com.example.Back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "labs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lab {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labId;

    private String labName;
    private String labCode;
    private String location;
    private String description;
    private Integer capacity;
    private String status;

    // Một Lab có nhiều Room
    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Room> rooms;

    @ManyToMany
    @JoinTable(
            name = "lab_room_slot",
            joinColumns = @JoinColumn(name = "lab_id"),
            inverseJoinColumns = @JoinColumn(name = "room_slot_id")
    )
    private List<RoomSlot> roomSlots;


}
