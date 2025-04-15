package com.example.agro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetVisitPaginationRequestDTO {
    @Builder.Default
    private Integer page = 0;
    
    @Builder.Default
    private Integer size = 10;
    
    @Builder.Default
    private String sortBy = "visitDate";
    
    @Builder.Default
    private String sortDirection = "DESC";
}