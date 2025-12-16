package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleRepuestoTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleRepuestoTrabajoRepository extends JpaRepository<DetalleRepuestoTrabajo,Long> {
    List<DetalleRepuestoTrabajo> findByTrabajoId(Long trabajoId);
}
