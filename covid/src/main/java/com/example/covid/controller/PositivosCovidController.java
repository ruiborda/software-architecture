package com.example.covid.controller;

import com.example.covid.dto.*;
import com.example.covid.service.PositivosCovidService;
import lombok.RequiredArgsConstructor;
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
} 