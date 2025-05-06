package com.tallerherramientas.tallerprueba.Servicios.Implementaciones.Detalles;

import com.tallerherramientas.tallerprueba.Modelo.Entidades.DetalleClienteTrabajo;
import com.tallerherramientas.tallerprueba.Servicios.Contratos.DetalleClienteTrabajoDAO;

import java.util.List;
import java.util.Optional;

public class DetalleClienteTrabajoDAOImp implements DetalleClienteTrabajoDAO {
    @Override
    public DetalleClienteTrabajo guardar(DetalleClienteTrabajo entidad) {
        return null;
    }

    @Override
    public List<DetalleClienteTrabajo> listar() {
        return null;
    }

    @Override
    public Optional<DetalleClienteTrabajo> obtenerPorId(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void eliminar(Long aLong) {

    }
}
