package com.werp.demo.repository.interfaces;

import com.werp.demo.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
