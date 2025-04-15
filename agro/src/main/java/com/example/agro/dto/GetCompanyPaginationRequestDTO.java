package com.example.agro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCompanyPaginationRequestDTO {
    @Builder.Default
    private Integer page = 0;
    
    @Builder.Default
    private Integer size = 10;
    
    @Builder.Default
    private String sortBy = "id";
    
    @Builder.Default
    private String sortDirection = "ASC";
}