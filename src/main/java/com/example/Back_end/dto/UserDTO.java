package com.example.Back_end.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Thông tin người dùng (DTO hiển thị trên Swagger)")
public class UserDTO {
    @Schema(example = "1")
    private Integer userId;

    @Schema(example = "johndoe")
    private String userName;

    @Schema(example = "john@example.com")
    private String email;

    @Schema(example = "ACTIVE")
    private String status;
}
