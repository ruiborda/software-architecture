package com.example.agro.service;

import com.example.agro.dto.CompanyRequestDTO;
import com.example.agro.dto.CompanyResponseDTO;
import com.example.agro.dto.GetCompanyByIdResponseDTO;
import com.example.agro.mapper.CompanyMapper;
import com.example.agro.model.Company;
import com.example.agro.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Transactional
    public CompanyResponseDTO createCompany(CompanyRequestDTO requestDTO) {
        Company company = companyMapper.toEntity(requestDTO);
        Company savedCompany = companyRepository.save(company);
        return companyMapper.toResponseDTO(savedCompany);
    }

    @Transactional(readOnly = true)
    public GetCompanyByIdResponseDTO getCompanyById(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
        return companyMapper.toGetByIdResponseDTO(company);
    }

    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> getCompaniesBySector(String sector) {
        return companyRepository.findBySector(sector).stream()
                .map(companyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> getCompaniesByDepartment(String department) {
        return companyRepository.findByDepartment(department).stream()
                .map(companyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> getCompaniesBySize(String size) {
        return companyRepository.findByCompanySize(size).stream()
                .map(companyMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
} 