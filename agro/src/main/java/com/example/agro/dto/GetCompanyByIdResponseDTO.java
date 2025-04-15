package com.example.agro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCompanyByIdResponseDTO {
    private UUID id;
    private String anonymousEmpId;
    private String sector;
    private String department;
    private String companySize;
    private Double estimatedMinSaleValue;
    private Double estimatedMaxSaleValue;
    private String exports;
} 