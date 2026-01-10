package com.tallerherramientas.tallerprueba.Services.Contratos;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleArticuloDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleArticuloTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;

import java.util.List;

public interface DetalleArticuloTrabajoDAO extends ServicioGenericoDAO<DetalleArticuloTrabajo,Long>{
    void guardarDetalleArticuloTrabajo(List<DetalleArticuloDTO> articuloDTOS, Trabajo trabajo);
}
