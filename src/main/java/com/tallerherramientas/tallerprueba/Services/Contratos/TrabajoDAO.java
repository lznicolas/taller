package com.tallerherramientas.tallerprueba.Services.Contratos;

import com.tallerherramientas.tallerprueba.Modelo.DTO.ReporteTrabajoDTO;
import com.tallerherramientas.tallerprueba.Modelo.DTO.TrabajoDTO;
import com.tallerherramientas.tallerprueba.Modelo.DTO.TrabajoDetalleDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TrabajoDAO extends ServicioGenericoDAO<Trabajo,Long> {

    Trabajo guardarDesdeDTO(TrabajoDTO dto);
    TrabajoDetalleDTO obtenerTrabajoDetallePorId(Long id);
    List<TrabajoDetalleDTO> listarDetalles();
    Trabajo actualizarDesdeDTO(Long id, TrabajoDTO dto);
    List<Trabajo> obtenerTrabajosPorClienteId(Long clienteId);
    List<TrabajoDTO>BuscarTrabajosPorCliente(Long clienteId);
    ReporteTrabajoDTO generarReporte(Long trabajoId);
    Trabajo actualizarCostoManoDeObra(Long trabajoId, BigDecimal costoManoDeObra);
}
