package com.example.licenses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMpsOperatingLicenseRequestDTO {
    private String department;
    private String province;
    private String district;
} 