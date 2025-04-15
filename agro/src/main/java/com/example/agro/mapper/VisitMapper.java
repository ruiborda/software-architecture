package com.example.agro.mapper;

import com.example.agro.dto.GetVisitPaginationResponseDTO;
import com.example.agro.dto.VisitRequestDTO;
import com.example.agro.dto.VisitResponseDTO;
import com.example.agro.model.Visit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    VisitMapper INSTANCE = Mappers.getMapper(VisitMapper.class);
    
    @Mapping(target = "id", ignore = true)
    Visit toEntity(VisitRequestDTO dto);
    
    VisitResponseDTO toResponseDTO(Visit entity);
    
    List<VisitResponseDTO> toResponseDTOList(List<Visit> entities);
    
    default GetVisitPaginationResponseDTO toPaginationResponseDTO(Page<Visit> page) {
        return GetVisitPaginationResponseDTO.builder()
                .content(toResponseDTOList(page.getContent()))
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .last(page.isLast())
                .build();
    }
}