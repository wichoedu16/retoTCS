package com.werp.demo.service;

import com.werp.demo.dto.request.MovimientoRequest;
import com.werp.demo.exception.BusinessException;
import com.werp.demo.model.Cuenta;
import com.werp.demo.model.Movimiento;
import com.werp.demo.repository.interfaces.ICuentaRepository;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class MovimientoServiceTest {
    @Autowired
    private MovimientoService movimientoService;

    @MockBean
    private ICuentaRepository cuentaRepository;

    @Test
    void debeRealizarMovimientoPositivo() {
        // Crear y guardar cuenta primero
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("225487");
        cuenta.setTipoCuenta("Ahorro");
        cuenta.setSaldoInicial(new BigDecimal("100.00"));
        cuenta.setEstado(true);
        cuentaRepository.save(cuenta);

        // Crear movimiento
        MovimientoRequest request = new MovimientoRequest();
        request.setNumeroCuenta("225487");
        request.setValor(new BigDecimal("600.00"));

        Movimiento resultado = movimientoService.realizar(request);

        assertEquals("CREDITO", resultado.getTipoMovimiento());
        assertEquals(new BigDecimal("700.00"), resultado.getSaldo());
    }

    @Test
    void debeLanzarExcepcionCuandoSaldoInsuficiente() {
        // Arrange
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("478758");
        cuenta.setSaldoInicial(new BigDecimal("100.00"));
        when(cuentaRepository.findByNumeroCuenta("478758"))
                .thenReturn(Optional.of(cuenta));

        MovimientoRequest request = new MovimientoRequest();
        request.setNumeroCuenta("478758");
        request.setValor(new BigDecimal("-150.00"));

        // Act & Assert
        assertThrows(BusinessException.class, () ->
                movimientoService.realizar(request));
    }
}