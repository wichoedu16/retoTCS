package com.werp.demo.controller;

import com.werp.demo.dto.request.ReporteRequest;
import com.werp.demo.dto.response.ReporteResponse;
import com.werp.demo.mapper.ReporteMapper;
import com.werp.demo.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReporteController {
    private final MovimientoService movimientoService;
    private final ReporteMapper reporteMapper;

    @PatchMapping("/reportes")
    public ResponseEntity<List<ReporteResponse>> generarReporte(@RequestBody ReporteRequest request) {
        List<ReporteResponse> res = movimientoService.generarReporte(request);
        return ResponseEntity.ok(reporteMapper.toResponseList(res));
    }
}