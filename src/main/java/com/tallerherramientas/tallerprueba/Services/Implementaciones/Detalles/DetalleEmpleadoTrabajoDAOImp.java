package com.tallerherramientas.tallerprueba.Services.Implementaciones.Detalles;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleEmpleadoDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleEmpleadoTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;
import com.tallerherramientas.tallerprueba.Repositories.DetalleEmpleadoTrabajoRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleEmpleadoTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DetalleEmpleadoTrabajoDAOImp implements DetalleEmpleadoTrabajoDAO {
    @Autowired
    private DetalleEmpleadoTrabajoRepository detalleEmpleadoTrabajoRepository;
    @Autowired
    private EmpleadoDAO empleadoDAO;
    @Override
    public DetalleEmpleadoTrabajo guardar(DetalleEmpleadoTrabajo entidad) {
        return detalleEmpleadoTrabajoRepository.save(entidad);
    }

    @Override
    public List<DetalleEmpleadoTrabajo> listar() {
        return detalleEmpleadoTrabajoRepository.findAll();
    }

    @Override
    public Optional<DetalleEmpleadoTrabajo> obtenerPorId(Long aLong) {
        Optional<DetalleEmpleadoTrabajo> detalleEmpleadoTrabajo = detalleEmpleadoTrabajoRepository.findById(aLong);
        return Optional.ofNullable(detalleEmpleadoTrabajo.orElse(null));
    }

    @Override
    public void eliminar(Long aLong) {
        detalleEmpleadoTrabajoRepository.deleteById(aLong);
    }


    @Override
    public void guardarDetalleEmpleadoTrabajo(List<DetalleEmpleadoDTO> empleadoDTOS, Trabajo trabajo) {
        for(DetalleEmpleadoDTO empleadoDTO : empleadoDTOS){
            Empleado empleado= empleadoDAO.obtenerPorId(empleadoDTO.getEmpleadoId())
                    .orElseThrow(()->new RuntimeException("Empleado no encontrado con el ID: "+ empleadoDTO.getEmpleadoId()));
            DetalleEmpleadoTrabajo detalleEmpleadoTrabajo = new DetalleEmpleadoTrabajo();

            detalleEmpleadoTrabajo.setTrabajo(trabajo);
            detalleEmpleadoTrabajo.setEmpleado(empleado);

            detalleEmpleadoTrabajoRepository.save(detalleEmpleadoTrabajo);
        }
    }
}
