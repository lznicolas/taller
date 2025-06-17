package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleClienteDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleClienteTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetalleClienteTrabajoRepository extends JpaRepository<DetalleClienteTrabajo,Long> {
    List<DetalleClienteTrabajo> findByClienteId(Long clienteId);
}
