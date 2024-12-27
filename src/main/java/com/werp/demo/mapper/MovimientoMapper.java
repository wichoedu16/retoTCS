package com.werp.demo.mapper;

import com.werp.demo.dto.response.MovimientoResponse;
import com.werp.demo.model.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    @Mapping(target = "numeroCuenta", source = "cuenta.numeroCuenta")
    MovimientoResponse toResponse(Movimiento movimiento);
    List<MovimientoResponse> toResponseList(List<Movimiento> movimientos);
}