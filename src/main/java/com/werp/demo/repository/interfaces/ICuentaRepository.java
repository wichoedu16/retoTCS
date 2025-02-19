package com.werp.demo.repository.interfaces;

import com.werp.demo.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    List<Cuenta> findByClienteId(Long clienteId);
}
