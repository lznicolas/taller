package com.tallerherramientas.tallerprueba.Services.Contratos;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleEmpleadoDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleEmpleadoTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;

import java.util.List;

public interface DetalleEmpleadoTrabajoDAO extends ServicioGenericoDAO<DetalleEmpleadoTrabajo,Long> {
    void guardarDetalleEmpleadoTrabajo(List<DetalleEmpleadoDTO> empleadoDTOS, Trabajo trabajo);
}
