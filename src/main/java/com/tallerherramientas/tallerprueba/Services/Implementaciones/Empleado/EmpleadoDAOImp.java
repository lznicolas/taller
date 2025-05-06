package com.tallerherramientas.tallerprueba.Services.Implementaciones.Empleado;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import com.tallerherramientas.tallerprueba.Repositories.EmpleadoRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoDAOImp implements EmpleadoDAO {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Override
    public Empleado guardar(Empleado entidad) {
        return empleadoRepository.save(entidad);
    }

    @Override
    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> obtenerPorId(Long aLong) {
        Optional<Empleado> empleado = empleadoRepository.findById(aLong);
        return Optional.ofNullable(empleado.orElse(null));
    }

    @Override
    public void eliminar(Long aLong) {
        empleadoRepository.deleteById(aLong);
    }
}
