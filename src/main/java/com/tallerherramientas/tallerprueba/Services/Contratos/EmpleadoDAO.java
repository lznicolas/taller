package com.tallerherramientas.tallerprueba.Services.Contratos;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import com.tallerherramientas.tallerprueba.Modelo.Enums.Especialidad;

import java.util.List;

public interface EmpleadoDAO extends ServicioGenericoDAO<Empleado,Long> {
    List<Especialidad> listarEspecialidades();
    Integer obtenerSiguienteLegajo(Especialidad especialidad);
    String ajustarLegajoSegunRegla(Empleado empleado);
}
