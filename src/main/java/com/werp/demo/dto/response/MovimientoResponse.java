package com.werp.demo.dto.response;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class MovimientoResponse {
    Long id;
    LocalDateTime fecha;
    String tipoMovimiento;
    BigDecimal valor;
    BigDecimal saldo;
    String numeroCuenta;
}