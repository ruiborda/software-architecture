package com.example.licenses.service;

import com.example.licenses.dto.MpsOperatingLicenseRequestDTO;
import com.example.licenses.dto.MpsOperatingLicenseResponseDTO;
import com.example.licenses.dto.GetMpsOperatingLicenseByIdResponseDTO;
import com.example.licenses.mapper.MpsOperatingLicenseMapper;
import com.example.licenses.model.MpsOperatingLicense;
import com.example.licenses.repository.MpsOperatingLicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MpsOperatingLicenseService {

    final private MpsOperatingLicenseRepository repository;
    final private MpsOperatingLicenseMapper mapper;

    public MpsOperatingLicenseResponseDTO createLicense(MpsOperatingLicenseRequestDTO requestDTO) {
        MpsOperatingLicense license = mapper.toEntity(requestDTO);
        MpsOperatingLicense savedLicense = repository.save(license);
        return mapper.toResponseDTO(savedLicense);
    }

    public GetMpsOperatingLicenseByIdResponseDTO getLicenseById(UUID id) {
        return repository.findById(id)
                .map(mapper::toGetByIdResponseDTO)
                .orElseThrow(() -> new RuntimeException("License not found with id: " + id));
    }

    public List<MpsOperatingLicenseResponseDTO> getLicensesByDepartment(String department) {
        return repository.findByDepartment(department)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MpsOperatingLicenseResponseDTO> getLicensesByProvince(String province) {
        return repository.findByProvince(province)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MpsOperatingLicenseResponseDTO> getLicensesByDistrict(String district) {
        return repository.findByDistrict(district)
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}