package com.example.Back_end.service.interf;

import com.example.Back_end.entity.RoomSlot;

import java.util.List;

public interface RoomSlotService {
    List<RoomSlot> getRoomSlotsBySlotId(Long slotId);
}
