package com.example.Back_end.controller;

import com.example.Back_end.dto.SupporterShirtRequestDTO;
import com.example.Back_end.dto.SupporterShirtResponseDTO;
import com.example.Back_end.service.interf.SupporterShirtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supporter-shirts")
@RequiredArgsConstructor
public class SupporterShirtController {

    private final SupporterShirtService shirtService;

    @PostMapping
    public SupporterShirtResponseDTO create(@RequestBody SupporterShirtRequestDTO dto) {
        return shirtService.create(dto);
    }

    @GetMapping("/{id}")
    public SupporterShirtResponseDTO get(@PathVariable Long id) {
        return shirtService.getById(id);
    }

    @GetMapping
    public List<SupporterShirtResponseDTO> getAll() {
        return shirtService.getAll();
    }

    @PutMapping("/{id}")
    public SupporterShirtResponseDTO update(@PathVariable Long id, @RequestBody SupporterShirtRequestDTO dto) {
        return shirtService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        shirtService.delete(id);
    }
}

