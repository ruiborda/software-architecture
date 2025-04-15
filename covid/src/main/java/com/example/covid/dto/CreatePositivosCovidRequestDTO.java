package com.example.covid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePositivosCovidRequestDTO {
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