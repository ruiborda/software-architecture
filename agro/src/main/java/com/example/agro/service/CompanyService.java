package com.example.agro.service;

import com.example.agro.dto.*;
import com.example.agro.mapper.CompanyMapper;
import com.example.agro.model.Company;
import com.example.agro.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final VisitService visitService;

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

    @Transactional(readOnly = true)
    public GetCompanyPaginationResponseDTO getPagination(GetCompanyPaginationRequestDTO request) {
        // Track this pagination request using the VisitService
        visitService.trackCompanyPagination();
        
        Sort.Direction direction = Sort.Direction.fromString(request.getSortDirection());
        Sort sort = Sort.by(direction, request.getSortBy());
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), sort);
        
        Page<Company> page = companyRepository.findAll(pageRequest);
        return companyMapper.toPaginationResponseDTO(page);
    }
}