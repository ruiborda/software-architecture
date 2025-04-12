package com.example.licenses.mapper;

import com.example.licenses.dto.MpsOperatingLicenseRequestDTO;
import com.example.licenses.dto.MpsOperatingLicenseResponseDTO;
import com.example.licenses.model.MpsOperatingLicense;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
// Elimina esta importación si quitas la línea INSTANCE
// import org.mapstruct.factory.Mappers;
// No es necesario importarlo, pero asegúrate de que no haya @Component aquí
// import org.springframework.stereotype.Component;

// Añade componentModel = "spring"
@Mapper(componentModel = "spring")
//@Component
public interface MpsOperatingLicenseMapper {

    // Elimina esta línea, ya no es necesaria con la inyección de Spring
    // MpsOperatingLicenseMapper INSTANCE = Mappers.getMapper(MpsOperatingLicenseMapper.class);

    MpsOperatingLicense toEntity(MpsOperatingLicenseRequestDTO dto);

    MpsOperatingLicenseResponseDTO toResponseDTO(MpsOperatingLicense entity);
}