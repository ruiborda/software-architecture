package com.example.covid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPositivosCovidPaginationRequestDTO {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String sortDirection;
} 