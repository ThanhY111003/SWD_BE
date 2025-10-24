package com.example.Back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestTypeId;
    private String typeName;
    private String description;
    private Integer approvalRequired;
}

