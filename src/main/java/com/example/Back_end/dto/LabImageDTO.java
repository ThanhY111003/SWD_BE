package com.example.Back_end.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LabImageDTO {
    private Long id;
    private Long labId;
    private String url;
    private String fileName;
    private Long uploadedBySupporterId;
    private LocalDateTime uploadedAt;
}

