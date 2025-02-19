package com.werp.demo.mapper;

import com.werp.demo.dto.request.ClienteRequest;
import com.werp.demo.dto.response.ClienteResponse;
import com.werp.demo.model.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteResponse toResponse(Cliente cliente);

    Cliente toEntity(ClienteRequest request);

    List<ClienteResponse> toResponseList(List<Cliente> clientes);

   default Cliente toEntityWithPersonaFields(ClienteRequest request) {
        if (request == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setGenero(request.getGenero());
        cliente.setIdentificacion(request.getIdentificacion());
        cliente.setDireccion(request.getDireccion());
        cliente.setTelefono(request.getTelefono());

        cliente.setPassword(request.getPassword());
        cliente.setEstado(request.getEsado());

        return cliente;
    }
}