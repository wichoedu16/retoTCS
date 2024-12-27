package com.werp.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CuentaRequest {
    @NotBlank
    private String numeroCuenta;
    @NotBlank
    private String tipoCuenta;
    @NotNull
    private BigDecimal saldoInicial;
    @NotNull
    private Long clienteId;
}