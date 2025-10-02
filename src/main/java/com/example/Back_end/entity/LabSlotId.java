package com.example.Back_end.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabSlotId implements Serializable {

    @Column(name = "slot_id")
    private Integer slotId;

    @Column(name = "lab_id")
    private Integer labId;
}
