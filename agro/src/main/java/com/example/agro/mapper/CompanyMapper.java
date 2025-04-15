package com.example.agro.mapper;

import com.example.agro.dto.CompanyRequestDTO;
import com.example.agro.dto.CompanyResponseDTO;
import com.example.agro.dto.GetCompanyByIdResponseDTO;
import com.example.agro.dto.GetCompanyPaginationResponseDTO;
import com.example.agro.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company toEntity(CompanyRequestDTO dto);
    CompanyResponseDTO toResponseDTO(Company entity);
    GetCompanyByIdResponseDTO toGetByIdResponseDTO(Company entity);
    
    List<CompanyResponseDTO> toResponseDTOList(List<Company> entities);

    default GetCompanyPaginationResponseDTO toPaginationResponseDTO(Page<Company> page) {
        return GetCompanyPaginationResponseDTO.builder()
                .content(toResponseDTOList(page.getContent()))
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .last(page.isLast())
                .build();
    }
}