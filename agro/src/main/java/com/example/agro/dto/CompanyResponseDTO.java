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
public class CompanyResponseDTO {
    private UUID id;
    private String anonymousEmpId;
    private Integer year;
    private Integer ciiu;
    private String ciiuDescription;
    private String sector;
    private String ubigeo;
    private String department;
    private String province;
    private String district;
    private String companySize;
    private Double estimatedMinSaleValue;
    private Double estimatedMaxSaleValue;
    private String exports;
    private Double estimatedMinFobDollar;
    private Double estimatedMaxFobDollar;
    private String creationDate;
} 