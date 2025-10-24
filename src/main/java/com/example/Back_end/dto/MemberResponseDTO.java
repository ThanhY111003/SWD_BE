package com.example.Back_end.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberResponseDTO {
    private Long memberId;
    private Long userId;
    private Long labId;
    private String memberCode;
    private String role;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinedAt;
}
