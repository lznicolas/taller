package com.tallerherramientas.tallerprueba.Services.Implementaciones.Empleado;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import com.tallerherramientas.tallerprueba.Modelo.Enums.Especialidad;
import com.tallerherramientas.tallerprueba.Repositories.EmpleadoRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.EmpleadoDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoDAOImp implements EmpleadoDAO {
    @Autowired
    private final EmpleadoRepository empleadoRepository;
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

    @Override
    public List<Especialidad> listarEspecialidades() {
        return Arrays.asList(Especialidad.values());
    }

    @Override
    public Integer obtenerSiguienteLegajo(Especialidad especialidad) {
        int base = especialidad == Especialidad.ADMINISTRATIVO ? 100 : 10000;
        Integer maxLegajo = empleadoRepository.findMaxLegajoByEspecialidad(especialidad);
        if (maxLegajo == null || maxLegajo < base) {
            return base;
        }
        return maxLegajo + 1;
    }

    @Override
    public String ajustarLegajoSegunRegla(Empleado empleado) {
        Especialidad especialidad = empleado.getEspecialidad();
        if (especialidad == null) {
            return empleado.getLegajo();
        }
        int minimo = especialidad == Especialidad.ADMINISTRATIVO ? 100 : 10000;
        int sugerido = Optional.ofNullable(obtenerSiguienteLegajo(especialidad)).orElse(minimo);
        try {
            int legajoNumero = Integer.parseInt(Optional.ofNullable(empleado.getLegajo()).orElse("0"));
            if (legajoNumero < minimo) {
                empleado.setLegajo(String.valueOf(sugerido));
            } else {
                empleado.setLegajo(String.valueOf(legajoNumero));
            }
        } catch (NumberFormatException e) {
            empleado.setLegajo(String.valueOf(sugerido));
        }
        return empleado.getLegajo();
    }
}
