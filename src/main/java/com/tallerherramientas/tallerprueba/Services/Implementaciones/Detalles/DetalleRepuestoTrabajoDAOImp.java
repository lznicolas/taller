package com.tallerherramientas.tallerprueba.Services.Implementaciones.Detalles;

import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleRepuestoTrabajo;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleRepuestoTrabajoDAO;

import java.util.List;
import java.util.Optional;

public class DetalleRepuestoTrabajoDAOImp implements DetalleRepuestoTrabajoDAO {
    @Override
    public DetalleRepuestoTrabajo guardar(DetalleRepuestoTrabajo entidad) {
        return null;
    }

    @Override
    public List<DetalleRepuestoTrabajo> listar() {
        return null;
    }

    @Override
    public Optional<DetalleRepuestoTrabajo> obtenerPorId(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void eliminar(Long aLong) {

    }
}
