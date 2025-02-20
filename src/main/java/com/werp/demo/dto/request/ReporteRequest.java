package com.werp.demo.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReporteRequest {

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String identificacion;
    private String numCuenta;
}
