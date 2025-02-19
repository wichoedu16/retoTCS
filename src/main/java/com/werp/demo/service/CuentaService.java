package com.werp.demo.service;

import com.werp.demo.dto.request.CuentaRequest;
import com.werp.demo.exception.BusinessException;
import com.werp.demo.model.Cliente;
import com.werp.demo.model.Cuenta;
import com.werp.demo.repository.interfaces.IClienteRepository;
import com.werp.demo.repository.interfaces.ICuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService {
    private final ICuentaRepository cuentaRepository;
    private final IClienteRepository clienteRepository;

    public Cuenta crear(CuentaRequest request) {
        if (cuentaRepository.existsByNumeroCuenta(request.getNumeroCuenta())) {
            throw new BusinessException("Cuenta ya existe");
        }

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new BusinessException("Cliente no encontrado"));

        if (request.getSaldoInicial().compareTo(BigDecimal.ZERO) >= 0){
            Cuenta cuenta = new Cuenta();
            cuenta.setNumeroCuenta(request.getNumeroCuenta());
            cuenta.setTipoCuenta(request.getTipoCuenta());
            cuenta.setSaldoInicial(request.getSaldoInicial());
            cuenta.setEstado(true);
            cuenta.setCliente(cliente);

            return cuentaRepository.save(cuenta);
        }else{
            throw new BusinessException("El saldo inicial no puede ser menor a 0");
        }
    }

    public Cuenta actualizar(Long id, CuentaRequest request) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cuenta no encontrada"));

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new BusinessException("Cliente no encontrado"));

        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setCliente(cliente);

        return cuentaRepository.save(cuenta);
    }

    public Cuenta obtenerPorNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new BusinessException("Numero de cuenta no encontrada"));
    }

    public List<Cuenta> obtenerPorCedula(String cedula) {
        Cliente cliente = clienteRepository.findByIdentificacion(cedula)
                .orElseThrow(() -> new BusinessException("Cliente con cédula: " + cedula + " no encontrado"));

        List<Cuenta> cuentas = cuentaRepository.findByClienteId(cliente.getId());

        if (cuentas.isEmpty()) {
            throw new BusinessException("No se encontraron cuentas para la cédula: " + cedula);
        }

        return cuentas;
    }

    public List<Cuenta> listarTodos() {
        return cuentaRepository.findAll();
    }

    public void eliminar(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new BusinessException("Cuenta no encontrada");
        }
        cuentaRepository.deleteById(id);
    }

}