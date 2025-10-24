package com.example.Back_end.service.interf;

import com.example.Back_end.dto.SupporterShirtRequestDTO;
import com.example.Back_end.dto.SupporterShirtResponseDTO;

import java.util.List;

public interface SupporterShirtService {
    SupporterShirtResponseDTO create(SupporterShirtRequestDTO dto);
    SupporterShirtResponseDTO getById(Long id);
    List<SupporterShirtResponseDTO> getAll();
    SupporterShirtResponseDTO update(Long id, SupporterShirtRequestDTO dto);
    void delete(Long id);
}
