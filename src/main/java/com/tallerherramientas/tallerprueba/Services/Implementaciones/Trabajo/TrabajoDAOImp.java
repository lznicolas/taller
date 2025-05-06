package com.tallerherramientas.tallerprueba.Services.Implementaciones.Trabajo;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;
import com.tallerherramientas.tallerprueba.Services.Contratos.TrabajoDAO;

import java.util.List;
import java.util.Optional;

public class TrabajoDAOImp implements TrabajoDAO {
    @Override
    public Trabajo guardar(Trabajo entidad) {
        return null;
    }

    @Override
    public List<Trabajo> listar() {
        return null;
    }

    @Override
    public Optional<Trabajo> obtenerPorId(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void eliminar(Long aLong) {

    }
}
