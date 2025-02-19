package com.werp.demo.dto.response;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CuentaResponse {
    Long id;
    String numeroCuenta;
    String tipoCuenta;
    BigDecimal saldoInicial;
    Boolean estado;
    String clienteNombre;
    String identificacion;
}
