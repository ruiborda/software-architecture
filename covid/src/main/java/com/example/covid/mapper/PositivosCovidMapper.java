package com.example.covid.mapper;

import com.example.covid.dto.*;
import com.example.covid.model.PositivosCovid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositivosCovidMapper {
    PositivosCovidMapper INSTANCE = Mappers.getMapper(PositivosCovidMapper.class);

    PositivosCovid toEntity(CreatePositivosCovidRequestDTO dto);

    GetPositivosCovidByIdResponseDTO toGetByIdResponseDTO(PositivosCovid entity);

    GetPositivosCovidByDepartamentoResponseDTO toGetByDepartamentoResponseDTO(String departamento, List<PositivosCovid> entities);

    GetPositivosCovidByEdadResponseDTO toGetByEdadResponseDTO(Integer edadMin, Integer edadMax, List<PositivosCovid> entities);

    GetPositivosCovidBySexoResponseDTO toGetBySexoResponseDTO(String sexo, List<PositivosCovid> entities);
} 