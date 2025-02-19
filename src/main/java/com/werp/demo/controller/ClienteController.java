package com.werp.demo.controller;

import com.werp.demo.dto.request.ClienteRequest;
import com.werp.demo.dto.response.ClienteResponse;
import com.werp.demo.mapper.ClienteMapper;
import com.werp.demo.model.Cliente;
import com.werp.demo.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;
    private final ClienteMapper mapper;

    @PostMapping
    public ResponseEntity<ClienteResponse> crear(@Valid @RequestBody ClienteRequest request) {
        Cliente cliente = mapper.toEntityWithPersonaFields(request);
        return new ResponseEntity<>(
                mapper.toResponse(clienteService.crear(cliente)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(
                mapper.toResponse(clienteService.buscarPorId(id))
        );
    }

    @GetMapping("/cedula/{cedula}")
    public ResponseEntity<ClienteResponse> obtenerPorCedula(@PathVariable String cedula) {
        return ResponseEntity.ok(
                mapper.toResponse(clienteService.buscarPorCedula(cedula))
        );
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() {
        return ResponseEntity.ok(
                mapper.toResponseList(clienteService.listarTodos())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequest request) {
        Cliente cliente = mapper.toEntity(request);
        return ResponseEntity.ok(
                mapper.toResponse(clienteService.actualizar(id, cliente))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}