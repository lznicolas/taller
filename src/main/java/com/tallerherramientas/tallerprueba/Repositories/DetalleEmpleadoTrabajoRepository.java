package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleEmpleadoTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleEmpleadoTrabajoRepository extends JpaRepository<DetalleEmpleadoTrabajo,Long> {
    List<DetalleEmpleadoTrabajo> findByTrabajoId(Long trabajoId);
}
