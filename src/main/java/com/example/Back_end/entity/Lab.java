package com.example.Back_end.entity;

import com.example.Back_end.entity.entity_enum.LabStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "lab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lab_id")
    private Integer labId;

    @Column(name = "lab_name", nullable = false, unique = true)
    private String labName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LabStatus status; // Available, InUse, Maintenance

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    private String location;

    @OneToMany(mappedBy = "lab", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @ManyToMany
    @JoinTable(
            name = "lab_schedule", // Bảng trung gian
            joinColumns = @JoinColumn(name = "lab_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id")
    )
    private List<Schedule> schedules;


    // Một Lab được quản lý bởi nhiều User
    @ManyToMany(mappedBy = "managedLabs")
    private Set<User> managers;

}
