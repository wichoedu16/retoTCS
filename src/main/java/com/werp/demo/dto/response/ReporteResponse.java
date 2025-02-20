package com.werp.demo.dto.response;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class ReporteResponse {
    LocalDateTime fecha;
    String cliente;
    String identificacion;
    String numeroCuenta;
    String tipo;
    BigDecimal saldoInicial;
    Boolean estado;
    BigDecimal movimiento;
    BigDecimal saldoDisponible;
}