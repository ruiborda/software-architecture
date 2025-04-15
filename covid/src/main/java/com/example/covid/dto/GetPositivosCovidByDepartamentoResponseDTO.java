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
public class GetPositivosCovidByDepartamentoResponseDTO {
    private String departamento;
    private List<PositivosCovidInfoDTO> casos;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PositivosCovidInfoDTO {
        private Long idPersona;
        private String fechaCorte;
        private String provincia;
        private String distrito;
        private String metododx;
        private Integer edad;
        private String sexo;
        private String fechaResultado;
        private String ubigeo;
    }
} 