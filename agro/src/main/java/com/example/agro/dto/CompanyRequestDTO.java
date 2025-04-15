package com.example.agro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDTO {
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