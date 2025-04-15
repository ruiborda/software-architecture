package com.example.covid.service;

import com.example.covid.dto.*;
import com.example.covid.mapper.PositivosCovidMapper;
import com.example.covid.model.PositivosCovid;
import com.example.covid.repository.PositivosCovidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PositivosCovidService {

    private final PositivosCovidRepository repository;
    private final PositivosCovidMapper mapper;

    @Transactional
    public GetPositivosCovidByIdResponseDTO create(CreatePositivosCovidRequestDTO request) {
        PositivosCovid entity = mapper.toEntity(request);
        PositivosCovid saved = repository.save(entity);
        return mapper.toGetByIdResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public GetPositivosCovidByIdResponseDTO getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toGetByIdResponseDTO)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }

    @Transactional(readOnly = true)
    public GetPositivosCovidByDepartamentoResponseDTO getByDepartamento(String departamento) {
        List<PositivosCovid> casos = repository.findByDepartamento(departamento);
        return mapper.toGetByDepartamentoResponseDTO(departamento, casos);
    }

    @Transactional(readOnly = true)
    public GetPositivosCovidByEdadResponseDTO getByEdadRange(Integer edadMin, Integer edadMax) {
        List<PositivosCovid> casos = repository.findByEdadBetween(edadMin, edadMax);
        return mapper.toGetByEdadResponseDTO(edadMin, edadMax, casos);
    }

    @Transactional(readOnly = true)
    public GetPositivosCovidBySexoResponseDTO getBySexo(String sexo) {
        List<PositivosCovid> casos = repository.findBySexo(sexo);
        return mapper.toGetBySexoResponseDTO(sexo, casos);
    }

    @Transactional(readOnly = true)
    public GetPositivosCovidPaginationResponseDTO getPagination(GetPositivosCovidPaginationRequestDTO request) {
        Sort.Direction direction = Sort.Direction.fromString(request.getSortDirection());
        Sort sort = Sort.by(direction, request.getSortBy());
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), sort);
        
        Page<PositivosCovid> page = repository.findAll(pageRequest);
        return mapper.toPaginationResponseDTO(page);
    }
} 