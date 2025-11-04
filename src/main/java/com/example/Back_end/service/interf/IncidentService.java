package com.example.Back_end.service.interf;

import com.example.Back_end.dto.IncidentRequestDTO;
import com.example.Back_end.dto.IncidentResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IncidentService {
    List<IncidentResponseDTO> getAll();
    IncidentResponseDTO getById(Long id);
    IncidentResponseDTO create(IncidentRequestDTO dto);
    IncidentResponseDTO update(Long id, IncidentRequestDTO dto);
    void delete(Long id);

    // Upload file đính kèm lên Firebase Storage và lưu URL vào Incident
    IncidentResponseDTO uploadAttachment(Long id, MultipartFile file) throws IOException;
}
