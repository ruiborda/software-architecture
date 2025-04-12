package com.example.licenses.controller;

import com.example.licenses.dto.MpsOperatingLicenseRequestDTO;
import com.example.licenses.dto.MpsOperatingLicenseResponseDTO;
import com.example.licenses.service.MpsOperatingLicenseService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/licenses")
public class MpsOperatingLicenseController {

    final private MpsOperatingLicenseService service;

    public MpsOperatingLicenseController(MpsOperatingLicenseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MpsOperatingLicenseResponseDTO> createLicense(
            @RequestBody MpsOperatingLicenseRequestDTO requestDTO) {
        return ResponseEntity.ok(service.createLicense(requestDTO));
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