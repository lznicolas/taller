package com.tallerherramientas.tallerprueba.Services.Implementaciones.Trabajo;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;
import com.tallerherramientas.tallerprueba.Repositories.TrabajoRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.TrabajoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TrabajoDAOImp implements TrabajoDAO {
    @Autowired
    private TrabajoRepository trabajoRepository;

    @Override
    public Trabajo guardar(Trabajo entidad) {
        return trabajoRepository.save(entidad);
    }

    @Override
    public List<Trabajo> listar() {
        return trabajoRepository.findAll();
    }

    @Override
    public Optional<Trabajo> obtenerPorId(Long aLong) {
        Optional<Trabajo> trabajo = trabajoRepository.findById(aLong);
        return Optional.ofNullable(trabajo.orElse(null));
    }

    @Override
    public void eliminar(Long aLong) {
        trabajoRepository.deleteById(aLong);
    }
}
