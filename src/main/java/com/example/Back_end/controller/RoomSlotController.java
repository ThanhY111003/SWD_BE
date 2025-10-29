package com.example.Back_end.controller;

import com.example.Back_end.entity.RoomSlot;
import com.example.Back_end.service.interf.RoomSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/room-slots")
@RequiredArgsConstructor
public class RoomSlotController {

    private final RoomSlotService roomSlotService;

    @GetMapping("/slot/{slotId}")
    public List<RoomSlot> getBySlotId(@PathVariable Long slotId) {
        return roomSlotService.getRoomSlotsBySlotId(slotId);
    }
}
