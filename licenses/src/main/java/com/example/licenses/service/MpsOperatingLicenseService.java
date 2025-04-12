package com.example.licenses.service;

import com.example.licenses.dto.MpsOperatingLicenseRequestDTO;
import com.example.licenses.dto.MpsOperatingLicenseResponseDTO;
import com.example.licenses.mapper.MpsOperatingLicenseMapper;
import com.example.licenses.model.MpsOperatingLicense;
import com.example.licenses.repository.MpsOperatingLicenseRepository;
// import lombok.AllArgsConstructor; // Elimina esta línea
import lombok.RequiredArgsConstructor; // Añade esta línea
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
// @AllArgsConstructor // Reemplaza esta línea
@RequiredArgsConstructor // Con esta línea
public class MpsOperatingLicenseService {

    // Estos campos final ahora serán inicializados por el constructor generado por @RequiredArgsConstructor
    final private MpsOperatingLicenseRepository repository;
    final private MpsOperatingLicenseMapper mapper;

    public MpsOperatingLicenseResponseDTO createLicense(MpsOperatingLicenseRequestDTO requestDTO) {
        MpsOperatingLicense license = mapper.toEntity(requestDTO);
        MpsOperatingLicense savedLicense = repository.save(license);
        return mapper.toResponseDTO(savedLicense);
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