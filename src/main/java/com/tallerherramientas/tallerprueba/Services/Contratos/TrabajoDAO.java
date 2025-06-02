package com.tallerherramientas.tallerprueba.Services.Contratos;

import com.tallerherramientas.tallerprueba.Modelo.DTO.TrabajoDTO;
import com.tallerherramientas.tallerprueba.Modelo.DTO.TrabajoDetalleDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;

public interface TrabajoDAO extends ServicioGenericoDAO<Trabajo,Long> {

    Trabajo guardarDesdeDTO(TrabajoDTO dto);
    TrabajoDetalleDTO obtenerTrabajoDetallePorId(Long id);

}
