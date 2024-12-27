package com.werp.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MovimientoRequest {
    @NotBlank
    private String numeroCuenta;
    @NotNull
    private BigDecimal valor;

    // Constructor público
    public MovimientoRequest(String numeroCuenta, BigDecimal valor) {
        this.numeroCuenta = numeroCuenta;
        this.valor = valor;
    }

    // Constructor sin argumentos público
    public MovimientoRequest() {
    }
}