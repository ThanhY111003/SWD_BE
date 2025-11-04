package com.example.Back_end.service.impl;

import com.example.Back_end.dto.LabImageDTO;
import com.example.Back_end.entity.Lab;
import com.example.Back_end.entity.LabImage;
import com.example.Back_end.entity.Staff;
import com.example.Back_end.entity.Supporter;
import com.example.Back_end.firebase.FirebaseStorageService;
import com.example.Back_end.repository.LabImageRepository;
import com.example.Back_end.repository.LabRepository;
import com.example.Back_end.repository.StaffRepository;
import com.example.Back_end.repository.SupporterRepository;
import com.example.Back_end.service.interf.LabImageService;
import com.example.Back_end.service.interf.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LabImageServiceImpl implements LabImageService {

    private final LabRepository labRepo;
    private final SupporterRepository supporterRepo;
    private final LabImageRepository labImageRepo;
    private final FirebaseStorageService storage;
    private final StaffRepository staffRepo;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public LabImageDTO upload(Long labId, Long supporterId, MultipartFile file) throws IOException {
        Lab lab = labRepo.findById(labId).orElseThrow(() -> new RuntimeException("Lab not found"));
        Supporter supporter = supporterRepo.findById(supporterId).orElse(null);

        FirebaseStorageService.Uploaded uploaded = storage.upload(file);

        LabImage img = new LabImage();
        img.setLab(lab);
        img.setUploadedBy(supporter);
        img.setFileName(uploaded.fileName());
        img.setUrl(uploaded.url());
        img = labImageRepo.save(img);

        // Notify all staff in this lab
        List<Staff> staffs = staffRepo.findByLabId(labId);
        for (Staff st : staffs) {
            notificationService.notifyStaff(
                    st,
                    "Ảnh mới được upload cho Lab " + lab.getLabName(),
                    "Supporter đã upload một ảnh mới."
            );
        }

        return toDTO(img);
    }

    @Override
    public List<LabImageDTO> list(Long labId) {
        Lab lab = labRepo.findById(labId).orElseThrow(() -> new RuntimeException("Lab not found"));
        return labImageRepo.findByLab(lab).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long imageId) throws IOException {
        LabImage img = labImageRepo.findById(imageId).orElseThrow(() -> new RuntimeException("Image not found"));
        // Delete from storage first
        storage.deleteByFileName(img.getFileName());
        labImageRepo.delete(img);
    }

    private LabImageDTO toDTO(LabImage img) {
        LabImageDTO dto = new LabImageDTO();
        dto.setId(img.getId());
        dto.setLabId(img.getLab().getLabId());
        dto.setUrl(img.getUrl());
        dto.setFileName(img.getFileName());
        dto.setUploadedBySupporterId(img.getUploadedBy() != null ? img.getUploadedBy().getSupporterId() : null);
        dto.setUploadedAt(img.getUploadedAt());
        return dto;
    }
}

