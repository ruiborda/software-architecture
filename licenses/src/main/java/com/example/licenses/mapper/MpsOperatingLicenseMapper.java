package com.example.licenses.mapper;

import com.example.licenses.dto.MpsOperatingLicenseRequestDTO;
import com.example.licenses.dto.MpsOperatingLicenseResponseDTO;
import com.example.licenses.dto.GetMpsOperatingLicenseByIdResponseDTO;
import com.example.licenses.model.MpsOperatingLicense;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MpsOperatingLicenseMapper {
    MpsOperatingLicenseMapper INSTANCE = Mappers.getMapper(MpsOperatingLicenseMapper.class);

    MpsOperatingLicense toEntity(MpsOperatingLicenseRequestDTO dto);

    MpsOperatingLicenseResponseDTO toResponseDTO(MpsOperatingLicense entity);

    GetMpsOperatingLicenseByIdResponseDTO toGetByIdResponseDTO(MpsOperatingLicense entity);
}