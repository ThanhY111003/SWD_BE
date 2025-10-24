package com.example.Back_end.service.interf;

import com.example.Back_end.dto.SupporterRequestDTO;
import com.example.Back_end.dto.SupporterResponseDTO;

import java.util.List;

public interface SupporterService {
    SupporterResponseDTO createSupporter(SupporterRequestDTO dto);
    SupporterResponseDTO getSupporterById(Long id);
    List<SupporterResponseDTO> getAllSupporters();
    SupporterResponseDTO updateSupporter(Long id, SupporterRequestDTO dto);
    void deleteSupporter(Long id);
}
