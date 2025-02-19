package com.werp.demo.service;

import com.werp.demo.exception.BusinessException;
import com.werp.demo.model.Cliente;
import com.werp.demo.repository.interfaces.IClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final IClienteRepository clienteRepository;

    public Cliente crear(Cliente cliente) {
        if (clienteRepository.existsByIdentificacion(cliente.getIdentificacion())) {
            throw new BusinessException("Ya existe un cliente con esta identificación");
        }
        cliente.setEstado(true);
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente no encontrado"));
    }


    public Cliente buscarPorCedula(String cedula) {
        return clienteRepository.findByIdentificacion(cedula)
                .orElseThrow(() -> new BusinessException("Cliente con cedula: " + cedula + " no encontrado "));
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente actualizar(Long id, Cliente cliente) {
        Cliente existente = buscarPorId(id);

        existente.setNombre(cliente.getNombre());
        existente.setDireccion(cliente.getDireccion());
        existente.setTelefono(cliente.getTelefono());

        return clienteRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new BusinessException("No se pudo eliminar cliente. Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
    }

}