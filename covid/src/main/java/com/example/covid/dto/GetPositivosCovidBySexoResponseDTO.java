package com.example.covid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPositivosCovidBySexoResponseDTO {
    private String sexo;
    private List<PositivosCovidInfoDTO> casos;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PositivosCovidInfoDTO {
        private Long idPersona;
        private String fechaCorte;
        private String departamento;
        private String provincia;
        private String distrito;
        private String metododx;
        private Integer edad;
        private String fechaResultado;
        private String ubigeo;
    }
} 