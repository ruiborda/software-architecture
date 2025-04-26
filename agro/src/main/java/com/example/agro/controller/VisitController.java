package com.example.agro.controller;

import com.example.agro.dto.VisitResponseDTO;
import com.example.agro.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @GetMapping
    public ResponseEntity<List<VisitResponseDTO>> getAllVisits() {
        return ResponseEntity.ok(visitService.getAllVisits());
    }
}