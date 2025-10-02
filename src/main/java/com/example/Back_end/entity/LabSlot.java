package com.example.Back_end.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "lab_slot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabSlot {

    @EmbeddedId
    private LabSlotId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("slotId")
    @JoinColumn(name = "slot_id", nullable = false)
    private Slot slot;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("labId")
    @JoinColumn(name = "lab_id", nullable = false)
    private Lab lab;
}
