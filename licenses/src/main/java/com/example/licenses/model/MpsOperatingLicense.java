package com.example.licenses.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "mps_operating_license")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MpsOperatingLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "departamento", length = 100)
    private String department;

    @Column(name = "provincia", length = 100)
    private String province;

    @Column(name = "distrito", length = 100)
    private String district;

    @Column(name = "ubigeo", length = 20)
    private String ubigeo;

    @Column(name = "fecha_corte")
    private String cutoffDate;

    @Column(name = "cod_licencia")
    private Integer licenseCode;

    @Column(name = "num_licencia", length = 20)
    private String licenseNumber;

    @Column(name = "f_licencia")
    private Long fLicense;

    @Column(name = "num_contribuyente", length = 20)
    private String taxpayerNumber;

    @Column(name = "area", precision = 10, scale = 2)
    private Long area;

    @Column(name = "giro", columnDefinition = "text")
    private String businessActivity;

    @Column(name = "estado", length = 20)
    private String status;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal amount;
}
