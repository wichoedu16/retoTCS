package com.werp.demo.service;

import com.werp.demo.dto.request.MovimientoRequest;
import com.werp.demo.dto.response.ReporteResponse;
import com.werp.demo.exception.BusinessException;
import com.werp.demo.model.Cliente;
import com.werp.demo.model.Cuenta;
import com.werp.demo.model.Movimiento;
import com.werp.demo.repository.interfaces.IClienteRepository;
import com.werp.demo.repository.interfaces.ICuentaRepository;
import com.werp.demo.repository.interfaces.IMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoService {
    private final IMovimientoRepository movimientoRepository;
    private final ICuentaRepository cuentaRepository;
    private final IClienteRepository clienteRepository;

    public Movimiento realizar(MovimientoRequest request) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(request.getNumeroCuenta())
                .orElseThrow(() -> new BusinessException("Cuenta no encontrada"));

        BigDecimal nuevoSaldo = cuenta.getSaldoInicial().add(request.getValor());
        if (request.getValor().compareTo(BigDecimal.ZERO) < 0 &&
                nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Saldo no disponible");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipoMovimiento(request.getValor().compareTo(BigDecimal.ZERO) > 0 ?
                "CREDITO" : "DEBITO");
        movimiento.setValor(request.getValor());
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);

        return movimientoRepository.save(movimiento);
    }

    public Movimiento obtenerPorId(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Movimiento no encontrado"));
    }

    public List<Movimiento> listarTodos() {
        return movimientoRepository.findAll();
    }

    public void eliminar(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new BusinessException("Movimiento no encontrado");
        }
        movimientoRepository.deleteById(id);
    }

    public List<ReporteResponse> generarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        return movimientoRepository.findByFechaBetween(
                        fechaInicio.atStartOfDay(),
                        fechaFin.atTime(LocalTime.MAX)).stream()
                .map(mov -> ReporteResponse.builder()
                        .fecha(mov.getFecha())
                        .cliente(mov.getCuenta().getCliente().getNombre())
                        .numeroCuenta(mov.getCuenta().getNumeroCuenta())
                        .tipo(mov.getCuenta().getTipoCuenta())
                        .saldoInicial(mov.getCuenta().getSaldoInicial())
                        .estado(mov.getCuenta().getEstado())
                        .movimiento(mov.getValor())
                        .saldoDisponible(mov.getSaldo())
                        .build())
                .collect(Collectors.toList());
    }
}