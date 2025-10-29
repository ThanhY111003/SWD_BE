package com.example.Back_end.service.impl;

import com.example.Back_end.entity.RoomSlot;
import com.example.Back_end.repository.RoomSlotRepository;
import com.example.Back_end.service.interf.RoomSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomSlotServiceImpl implements RoomSlotService {

    private final RoomSlotRepository roomSlotRepository;

    @Override
    public List<RoomSlot> getRoomSlotsBySlotId(Long slotId) {
        return roomSlotRepository.findBySlotId(slotId);
    }
}
