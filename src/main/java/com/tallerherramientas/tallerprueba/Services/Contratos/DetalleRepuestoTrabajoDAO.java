package com.tallerherramientas.tallerprueba.Services.Contratos;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleRepuestoDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleRepuestoTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;

import java.util.List;

public interface DetalleRepuestoTrabajoDAO extends ServicioGenericoDAO<DetalleRepuestoTrabajo,Long>{
    void guardarDetalleRepuestoTrabajo(List<DetalleRepuestoDTO>repuestoDTOS, Trabajo trabajo);
}
