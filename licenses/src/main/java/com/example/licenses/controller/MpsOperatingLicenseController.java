package com.example.licenses.controller;

import com.example.licenses.dto.MpsOperatingLicenseRequestDTO;
import com.example.licenses.dto.MpsOperatingLicenseResponseDTO;
import com.example.licenses.dto.GetMpsOperatingLicenseByIdResponseDTO;
import com.example.licenses.service.MpsOperatingLicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/licenses")
@RequiredArgsConstructor
public class MpsOperatingLicenseController {

    private final MpsOperatingLicenseService service;

    @PostMapping
    public ResponseEntity<MpsOperatingLicenseResponseDTO> createLicense(
            @RequestBody MpsOperatingLicenseRequestDTO requestDTO) {
        return ResponseEntity.ok(service.createLicense(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMpsOperatingLicenseByIdResponseDTO> getLicenseById(
            @PathVariable UUID id) {
        return ResponseEntity.ok(service.getLicenseById(id));
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<MpsOperatingLicenseResponseDTO>> getLicensesByDepartment(
            @PathVariable String department) {
        return ResponseEntity.ok(service.getLicensesByDepartment(department));
    }

    @GetMapping("/province/{province}")
    public ResponseEntity<List<MpsOperatingLicenseResponseDTO>> getLicensesByProvince(
            @PathVariable String province) {
        return ResponseEntity.ok(service.getLicensesByProvince(province));
    }

    @GetMapping("/district/{district}")
    public ResponseEntity<List<MpsOperatingLicenseResponseDTO>> getLicensesByDistrict(
            @PathVariable String district) {
        return ResponseEntity.ok(service.getLicensesByDistrict(district));
    }
} 