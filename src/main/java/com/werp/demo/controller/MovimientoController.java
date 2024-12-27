package com.werp.demo.controller;

import com.werp.demo.dto.request.MovimientoRequest;
import com.werp.demo.dto.response.MovimientoResponse;
import com.werp.demo.mapper.MovimientoMapper;
import com.werp.demo.service.MovimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@RequiredArgsConstructor
public class MovimientoController {
    private final MovimientoService movimientoService;
    private final MovimientoMapper mapper;

    @PostMapping
    public ResponseEntity<MovimientoResponse> crear(@Valid @RequestBody MovimientoRequest request) {
        return new ResponseEntity<>(
                mapper.toResponse(movimientoService.realizar(request)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                mapper.toResponse(movimientoService.obtenerPorId(id))
        );
    }

    @GetMapping
    public ResponseEntity<List<MovimientoResponse>> listar() {
        return ResponseEntity.ok(
                mapper.toResponseList(movimientoService.listarTodos())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        movimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
