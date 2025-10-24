package com.example.Back_end.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supporters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supporter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supporterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String supporterCode;
    private String status;
    private LocalDateTime registeredAt;
}
