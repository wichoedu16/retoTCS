package com.werp.demo.controller;

import com.werp.demo.dto.request.CuentaRequest;
import com.werp.demo.dto.response.CuentaResponse;
import com.werp.demo.mapper.CuentaMapper;
import com.werp.demo.model.Cuenta;
import com.werp.demo.service.CuentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
public class CuentaController {
    private final CuentaService cuentaService;
    private final CuentaMapper mapper;

    @PostMapping
    public ResponseEntity<CuentaResponse> crear(@Valid @RequestBody CuentaRequest request) {
        Cuenta cuenta = cuentaService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toResponse(cuenta));
    }

    @GetMapping
    public ResponseEntity<List<CuentaResponse>> listarTodos() {
        List<Cuenta> cuentas = cuentaService.listarTodos();
        return ResponseEntity.ok(mapper.toResponseList(cuentas));
    }

    @GetMapping("/numero/{numeroCuenta}")
    public ResponseEntity<CuentaResponse> obtenerPorNumeroCuenta(@PathVariable String numeroCuenta) {
        return ResponseEntity.ok(mapper.toResponse(cuentaService.obtenerPorNumeroCuenta(numeroCuenta)));
    }

    @GetMapping("/cliente/{cedula}")
    public ResponseEntity<List<CuentaResponse>> obtenerPorCedula(@PathVariable String cedula) {
        List<Cuenta> cuentas = cuentaService.obtenerPorCedula(cedula);
        List<CuentaResponse> response = mapper.toResponseList(cuentas);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CuentaRequest request) {
        Cuenta cuenta = cuentaService.actualizar(id, request);
        return ResponseEntity.ok(mapper.toResponse(cuenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cuentaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}