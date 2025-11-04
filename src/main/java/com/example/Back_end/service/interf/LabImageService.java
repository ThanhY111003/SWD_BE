package com.example.Back_end.service.interf;

import com.example.Back_end.dto.LabImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LabImageService {
    LabImageDTO upload(Long labId, Long supporterId, MultipartFile file) throws IOException;
    List<LabImageDTO> list(Long labId);
    void delete(Long imageId) throws IOException;
}

