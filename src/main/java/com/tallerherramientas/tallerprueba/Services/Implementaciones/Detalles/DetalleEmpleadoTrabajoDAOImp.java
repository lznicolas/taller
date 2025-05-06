package com.tallerherramientas.tallerprueba.Servicios.Implementaciones.Detalles;

import com.tallerherramientas.tallerprueba.Modelo.Entidades.DetalleEmpleadoTrabajo;
import com.tallerherramientas.tallerprueba.Servicios.Contratos.DetalleEmpleadoTrabajoDAO;

import java.util.List;
import java.util.Optional;

public class DetalleEmpleadoTrabajoDAOImp implements DetalleEmpleadoTrabajoDAO {
    @Override
    public DetalleEmpleadoTrabajo guardar(DetalleEmpleadoTrabajo entidad) {
        return null;
    }

    @Override
    public List<DetalleEmpleadoTrabajo> listar() {
        return null;
    }

    @Override
    public Optional<DetalleEmpleadoTrabajo> obtenerPorId(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void eliminar(Long aLong) {

    }
}
