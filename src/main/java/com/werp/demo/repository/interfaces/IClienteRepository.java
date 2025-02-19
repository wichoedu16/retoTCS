package com.werp.demo.repository.interfaces;

import com.werp.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByIdentificacion(String identificacion);
    Optional<Cliente> findByIdentificacion(String cedula);
}
