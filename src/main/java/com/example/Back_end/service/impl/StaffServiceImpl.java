package com.example.Back_end.service.impl;

import com.example.Back_end.dto.StaffRequestDTO;
import com.example.Back_end.dto.StaffResponseDTO;
import com.example.Back_end.entity.Staff;
import com.example.Back_end.repository.LabRepository;
import com.example.Back_end.repository.StaffRepository;
import com.example.Back_end.repository.UserRepository;
import com.example.Back_end.service.interf.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final LabRepository labRepository;

    @Override
    public StaffResponseDTO create(StaffRequestDTO dto) {
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var lab = labRepository.findById(dto.getLabId())
                .orElseThrow(() -> new RuntimeException("Lab not found"));

        Staff staff = new Staff();
        staff.setUser(user);
        staff.setLab(lab);
        staff.setStaffCode(dto.getStaffCode());
        staff.setPosition(dto.getPosition());
        staff.setDepartment(dto.getDepartment());
        staffRepository.save(staff);

        return toDto(staff);
    }

    @Override
    public StaffResponseDTO getById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        return toDto(staff);
    }

    @Override
    public List<StaffResponseDTO> getAll() {
        return staffRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public StaffResponseDTO update(Long id, StaffRequestDTO dto) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        if (dto.getLabId() != null) {
            var lab = labRepository.findById(dto.getLabId())
                    .orElseThrow(() -> new RuntimeException("Lab not found"));
            staff.setLab(lab);
        }
        staff.setStaffCode(dto.getStaffCode());
        staff.setPosition(dto.getPosition());
        staff.setDepartment(dto.getDepartment());

        staffRepository.save(staff);
        return toDto(staff);
    }

    @Override
    public void delete(Long id) {
        staffRepository.deleteById(id);
    }

    private StaffResponseDTO toDto(Staff staff) {
        StaffResponseDTO dto = new StaffResponseDTO();
        dto.setStaffId(staff.getStaffId());
        dto.setUserId(staff.getUser().getUserId());
        dto.setLabId(staff.getLab().getLabId());
        dto.setStaffCode(staff.getStaffCode());
        dto.setPosition(staff.getPosition());
        dto.setDepartment(staff.getDepartment());
        dto.setHiredAt(staff.getHiredAt());
        return dto;
    }
}

