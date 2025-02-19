package com.werp.demo.mapper;

import com.werp.demo.dto.request.CuentaRequest;
import com.werp.demo.dto.response.CuentaResponse;
import com.werp.demo.model.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    @Mapping(target = "clienteNombre", source = "cliente.nombre")
    @Mapping(target = "identificacion", source = "cliente.identificacion")
    CuentaResponse toResponse(Cuenta cuenta);
    Cuenta toEntity(CuentaRequest request);
    List<CuentaResponse> toResponseList(List<Cuenta> cuentas);
}
