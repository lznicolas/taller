package com.tallerherramientas.tallerprueba.Services.Contratos;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleClienteDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleClienteTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;

import java.util.List;

public interface DetalleClienteTrabajoDAO extends ServicioGenericoDAO<DetalleClienteTrabajo,Long> {
    void guardarDetalleClienteTrabajo(List<DetalleClienteDTO> dto, Trabajo trabajo);
}
