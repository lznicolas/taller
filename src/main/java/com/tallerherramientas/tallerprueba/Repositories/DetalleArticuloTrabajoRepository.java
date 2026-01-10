package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleArticuloTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleArticuloTrabajoRepository extends JpaRepository<DetalleArticuloTrabajo,Long> {
    List<DetalleArticuloTrabajo> findByTrabajoId(Long trabajoId);
}
