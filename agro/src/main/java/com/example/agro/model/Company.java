package com.example.agro.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "empresa") // Tabla en español
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // Mapeo de columnas (en minúscula español) a atributos en inglés
    @Column(name = "id_anonimo_emp")
    private String anonymousEmpId;

    @Column(name = "anio")
    private Integer year;

    @Column(name = "ciiu")
    private Integer ciiu;

    @Column(name = "descciiu")
    private String ciiuDescription;

    @Column(name = "sector")
    private String sector;

    @Column(name = "ubigeo")
    private String ubigeo;

    @Column(name = "departamento")
    private String department;

    @Column(name = "provincia")
    private String province;

    @Column(name = "distrito")
    private String district;

    @Column(name = "tamanio_emp")
    private String companySize;

    @Column(name = "valor_estimado_minimo_venta")
    private Double estimatedMinSaleValue;

    @Column(name = "valor_estimado_maximo_venta")
    private Double estimatedMaxSaleValue;

    @Column(name = "exporta")
    private String exports;

    @Column(name = "valor_estimado_minimo_fob_dolar")
    private Double estimatedMinFobDollar;

    @Column(name = "valor_estimado_maximo_fob_dolar")
    private Double estimatedMaxFobDollar;

    @Column(name = "fec_creacion")
    private String creationDate;
}
