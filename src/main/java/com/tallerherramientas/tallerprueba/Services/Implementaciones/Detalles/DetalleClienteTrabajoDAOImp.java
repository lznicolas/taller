package com.tallerherramientas.tallerprueba.Services.Implementaciones.Detalles;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleClienteDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Cliente;
import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleClienteTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;
import com.tallerherramientas.tallerprueba.Repositories.DetalleClienteTrabajoRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.ClienteDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleClienteTrabajoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DetalleClienteTrabajoDAOImp implements DetalleClienteTrabajoDAO {
    @Autowired
    private DetalleClienteTrabajoRepository detalleClienteTrabajoRepository;
    @Autowired
    private ClienteDAO clienteDAO;
    @Override
    public DetalleClienteTrabajo guardar(DetalleClienteTrabajo entidad) {
        return detalleClienteTrabajoRepository.save(entidad);
    }

    @Override
    public List<DetalleClienteTrabajo> listar() {
        return detalleClienteTrabajoRepository.findAll();
    }

    @Override
    public Optional<DetalleClienteTrabajo> obtenerPorId(Long aLong) {
        Optional<DetalleClienteTrabajo>detalleClienteTrabajo = detalleClienteTrabajoRepository.findById(aLong);
        return Optional.ofNullable(detalleClienteTrabajo.orElse(null));
    }

    @Override
    public void eliminar(Long aLong) {
        detalleClienteTrabajoRepository.deleteById(aLong);
    }

    public void guardarDetalleClienteTrabajo(List<DetalleClienteDTO> clienteDTOS, Trabajo trabajo) {
        for(DetalleClienteDTO clienteDTO : clienteDTOS){
            Cliente cliente= clienteDAO.obtenerPorId(clienteDTO.getClienteId())
                        .orElseThrow(()->new RuntimeException("Cliente no encotrado con el ID: "+clienteDTO.getClienteId()));
            DetalleClienteTrabajo detalleClienteTrabajo = new DetalleClienteTrabajo();

            detalleClienteTrabajo.setTrabajo(trabajo);
            detalleClienteTrabajo.setCliente(cliente);

            detalleClienteTrabajoRepository.save(detalleClienteTrabajo);
        }
    }

    @Override
    public List<DetalleClienteTrabajo> obtenerPorClienteId(Long clienteId) {
        return detalleClienteTrabajoRepository.findByClienteId(clienteId);
    }

}
