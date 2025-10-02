package com.example.Back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "permission_name", nullable = false, unique = true)
    private String permissionName;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}