package com.example.Back_end.entity;

import com.example.Back_end.entity.entity_enum.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

    // Người đặt booking
    @ManyToOne
    @JoinColumn(name = "booked_by", nullable = false)
    private User bookedBy;


    // Người duyệt booking
    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_id", nullable = false)
    private Lab lab;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status; // Pending, Approved, Rejected, Completed

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Attendance attendance;

    @OneToOne(mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Feedback feedback;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<Request> requests;

}
