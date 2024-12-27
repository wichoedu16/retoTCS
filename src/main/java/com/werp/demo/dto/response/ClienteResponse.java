package com.werp.demo.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClienteResponse {
    Long id;
    String nombre;
    String identificacion;
    String direccion;
    String telefono;
    Boolean estado;
}