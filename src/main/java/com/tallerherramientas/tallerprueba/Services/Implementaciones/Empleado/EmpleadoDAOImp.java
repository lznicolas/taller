package com.tallerherramientas.tallerprueba.Servicios.Implementaciones.Empleado;

import com.tallerherramientas.tallerprueba.Modelo.Entidades.Empleado;
import com.tallerherramientas.tallerprueba.Servicios.Contratos.EmpleadoDAO;

import java.util.List;
import java.util.Optional;

public class EmpleadoDAOImp implements EmpleadoDAO {
    @Override
    public Empleado guardar(Empleado entidad) {
        return null;
    }

    @Override
    public List<Empleado> listar() {
        return null;
    }

    @Override
    public Optional<Empleado> obtenerPorId(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void eliminar(Long aLong) {

    }
}
