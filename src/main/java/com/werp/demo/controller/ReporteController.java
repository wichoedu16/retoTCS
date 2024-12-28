package com.werp.demo.controller;

import com.werp.demo.dto.response.ReporteResponse;
import com.werp.demo.service.MovimientoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ReporteController {
    private final MovimientoService movimientoService;

    public ReporteController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping("/reportes")
    public ResponseEntity<List<ReporteResponse>> generarReporte(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {
        return ResponseEntity.ok(movimientoService.generarReporte(fechaInicio, fechaFin));
    }
}