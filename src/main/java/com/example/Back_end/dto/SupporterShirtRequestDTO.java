package com.example.Back_end.dto;

import lombok.Data;

@Data
public class SupporterShirtRequestDTO {
    private Long supporterId;
    private String shirtSize;
    private String shirtColor;
    private Integer quantity;
}

