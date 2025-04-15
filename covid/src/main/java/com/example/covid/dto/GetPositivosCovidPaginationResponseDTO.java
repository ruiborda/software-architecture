package com.example.covid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPositivosCovidPaginationResponseDTO {
    private List<PositivosCovidInfoDTO> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PositivosCovidInfoDTO {
        private UUID id;
        private Long idPersona;
        private String fechaCorte;
        private String departamento;
        private String provincia;
        private String distrito;
        private String metododx;
        private Integer edad;
        private String sexo;
        private String fechaResultado;
        private String ubigeo;
    }
} 