package com.example.agro.mapper;

import com.example.agro.dto.CompanyRequestDTO;
import com.example.agro.dto.CompanyResponseDTO;
import com.example.agro.dto.GetCompanyByIdResponseDTO;
import com.example.agro.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company toEntity(CompanyRequestDTO dto);
    CompanyResponseDTO toResponseDTO(Company entity);
    GetCompanyByIdResponseDTO toGetByIdResponseDTO(Company entity);
} 