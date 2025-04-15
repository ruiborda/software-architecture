package com.example.covid.controller;

import com.example.covid.dto.*;
import com.example.covid.service.PositivosCovidService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/positivos-covid")
@RequiredArgsConstructor
public class PositivosCovidController {

    private final PositivosCovidService service;

    @PostMapping
    public ResponseEntity<GetPositivosCovidByIdResponseDTO> create(@RequestBody CreatePositivosCovidRequestDTO request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPositivosCovidByIdResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<GetPositivosCovidByDepartamentoResponseDTO> getByDepartamento(
            @PathVariable String departamento) {
        return ResponseEntity.ok(service.getByDepartamento(departamento));
    }

    @GetMapping("/edad")
    public ResponseEntity<GetPositivosCovidByEdadResponseDTO> getByEdadRange(
            @RequestParam Integer edadMin,
            @RequestParam Integer edadMax) {
        return ResponseEntity.ok(service.getByEdadRange(edadMin, edadMax));
    }

    @GetMapping("/sexo/{sexo}")
    public ResponseEntity<GetPositivosCovidBySexoResponseDTO> getBySexo(
            @PathVariable String sexo) {
        return ResponseEntity.ok(service.getBySexo(sexo));
    }

    @GetMapping("/pagination")
    public ResponseEntity<GetPositivosCovidPaginationResponseDTO> getPagination(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        
        GetPositivosCovidPaginationRequestDTO request = GetPositivosCovidPaginationRequestDTO.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();
                
        return ResponseEntity.ok(service.getPagination(request));
    }
} 