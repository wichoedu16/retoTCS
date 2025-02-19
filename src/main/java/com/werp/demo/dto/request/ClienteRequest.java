package com.werp.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "La identificación es obligatoria")
    private String identificacion;
    private String genero;
    private String direccion;
    private String telefono;
    private String password;
    private Boolean esado;
}