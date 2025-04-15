package com.example.licenses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMpsOperatingLicenseByIdResponseDTO {
    private UUID id;
    private String department;
    private String province;
    private String district;
    private String ubigeo;
    private String cutoffDate;
    private Integer licenseCode;
    private String licenseNumber;
    private Long fLicense;
    private String taxpayerNumber;
    private Long area;
    private String businessActivity;
    private String status;
    private BigDecimal amount;
} 