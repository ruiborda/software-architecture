package com.example.covid.repository;

import com.example.covid.model.PositivosCovid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PositivosCovidRepository extends JpaRepository<PositivosCovid, UUID>, JpaSpecificationExecutor<PositivosCovid> {
    List<PositivosCovid> findByDepartamento(String departamento);
    List<PositivosCovid> findByEdadBetween(Integer edadMin, Integer edadMax);
    List<PositivosCovid> findBySexo(String sexo);
} 