package com.example.Back_end.controller;

import com.example.Back_end.dto.LabImageDTO;
import com.example.Back_end.service.interf.LabImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/labs")
@RequiredArgsConstructor
public class LabImageController {

    private final LabImageService labImageService;

    @Operation(summary = "Upload an image for a lab", description = "A supporter uploads an image for a specific lab. Staff members associated with that lab will receive a notification about the new image.")
    @PostMapping(value = "/{labId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LabImageDTO> upload(
            @Parameter(description = "The ID of the lab to upload the image for") @PathVariable Long labId,
            @Parameter(description = "The ID of the supporter uploading the image") @RequestParam Long supporterId,
            @RequestPart("file") MultipartFile file
    ) throws Exception {
        return ResponseEntity.ok(labImageService.upload(labId, supporterId, file));
    }

    @Operation(summary = "List all images for a lab")
    @GetMapping("/{labId}/images")
    public ResponseEntity<List<LabImageDTO>> list(@Parameter(description = "The ID of the lab") @PathVariable Long labId) {
        return ResponseEntity.ok(labImageService.list(labId));
    }

    @Operation(summary = "Delete an image")
    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<Void> delete(@Parameter(description = "The ID of the image to be deleted") @PathVariable Long imageId) throws Exception {
        labImageService.delete(imageId);
        return ResponseEntity.noContent().build();
    }
}
