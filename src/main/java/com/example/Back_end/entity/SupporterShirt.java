package com.example.Back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "supporter_shirts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupporterShirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shirtId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supporter_id")
    private Supporter supporter;

    private String shirtSize;
    private String shirtColor;
    private Integer quantity;
    private LocalDateTime issuedAt;
}
