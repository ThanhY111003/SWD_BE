package com.example.Back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "lab_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab_id", nullable = false)
    private Lab lab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supporter_id")
    private Supporter uploadedBy;

    // Tên file trong bucket để xoá dễ dàng
    @Column(nullable = false)
    private String fileName;

    // URL công khai để hiển thị ảnh
    @Column(columnDefinition = "TEXT", nullable = false)
    private String url;

    private LocalDateTime uploadedAt = LocalDateTime.now();
}

