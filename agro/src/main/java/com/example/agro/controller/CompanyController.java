package com.example.agro.controller;

import com.example.agro.dto.*;
import com.example.agro.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(@RequestBody CompanyRequestDTO requestDTO) {
        return ResponseEntity.ok(companyService.createCompany(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCompanyByIdResponseDTO> getCompanyById(@PathVariable UUID id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @GetMapping("/sector/{sector}")
    public ResponseEntity<List<CompanyResponseDTO>> getCompaniesBySector(@PathVariable String sector) {
        return ResponseEntity.ok(companyService.getCompaniesBySector(sector));
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<CompanyResponseDTO>> getCompaniesByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(companyService.getCompaniesByDepartment(department));
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<List<CompanyResponseDTO>> getCompaniesBySize(@PathVariable String size) {
        return ResponseEntity.ok(companyService.getCompaniesBySize(size));
    }

    @GetMapping("/pagination")
    public ResponseEntity<GetCompanyPaginationResponseDTO> getPagination(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        
        GetCompanyPaginationRequestDTO requestDTO = GetCompanyPaginationRequestDTO.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();
                
        return ResponseEntity.ok(companyService.getPagination(requestDTO));
    }
}