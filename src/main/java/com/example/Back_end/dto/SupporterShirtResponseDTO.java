package com.example.Back_end.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SupporterShirtResponseDTO {
    private Long shirtId;
    private Long supporterId;
    private String shirtSize;
    private String shirtColor;
    private Integer quantity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime issuedAt;
}
