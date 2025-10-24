package com.example.Back_end.service.impl;

import com.example.Back_end.dto.SupporterShirtRequestDTO;
import com.example.Back_end.dto.SupporterShirtResponseDTO;
import com.example.Back_end.entity.Supporter;
import com.example.Back_end.entity.SupporterShirt;
import com.example.Back_end.repository.SupporterRepository;
import com.example.Back_end.repository.SupporterShirtRepository;
import com.example.Back_end.service.interf.SupporterShirtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupporterShirtServiceImpl implements SupporterShirtService {

    private final SupporterShirtRepository shirtRepository;
    private final SupporterRepository supporterRepository;

    @Override
    public SupporterShirtResponseDTO create(SupporterShirtRequestDTO dto) {
        Supporter supporter = supporterRepository.findById(dto.getSupporterId())
                .orElseThrow(() -> new RuntimeException("Supporter not found"));

        SupporterShirt shirt = new SupporterShirt();
        shirt.setSupporter(supporter);
        shirt.setShirtSize(dto.getShirtSize());
        shirt.setShirtColor(dto.getShirtColor());
        shirt.setQuantity(dto.getQuantity());
        shirtRepository.save(shirt);
        return toDto(shirt);
    }

    @Override
    public SupporterShirtResponseDTO getById(Long id) {
        SupporterShirt shirt = shirtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shirt not found"));
        return toDto(shirt);
    }

    @Override
    public List<SupporterShirtResponseDTO> getAll() {
        return shirtRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public SupporterShirtResponseDTO update(Long id, SupporterShirtRequestDTO dto) {
        SupporterShirt shirt = shirtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shirt not found"));

        shirt.setShirtSize(dto.getShirtSize());
        shirt.setShirtColor(dto.getShirtColor());
        shirt.setQuantity(dto.getQuantity());
        shirtRepository.save(shirt);
        return toDto(shirt);
    }

    @Override
    public void delete(Long id) {
        shirtRepository.deleteById(id);
    }

    private SupporterShirtResponseDTO toDto(SupporterShirt shirt) {
        SupporterShirtResponseDTO dto = new SupporterShirtResponseDTO();
        dto.setShirtId(shirt.getShirtId());
        dto.setSupporterId(shirt.getSupporter().getSupporterId());
        dto.setShirtSize(shirt.getShirtSize());
        dto.setShirtColor(shirt.getShirtColor());
        dto.setQuantity(shirt.getQuantity());
        dto.setIssuedAt(shirt.getIssuedAt());
        return dto;
    }
}

