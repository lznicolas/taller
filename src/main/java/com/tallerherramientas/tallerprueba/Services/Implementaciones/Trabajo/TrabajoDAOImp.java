package com.tallerherramientas.tallerprueba.Services.Implementaciones.Trabajo;

import com.tallerherramientas.tallerprueba.Modelo.DTO.*;
import com.tallerherramientas.tallerprueba.Modelo.Entities.*;
import com.tallerherramientas.tallerprueba.Modelo.Enums.TipoTrabajo;
import com.tallerherramientas.tallerprueba.Repositories.*;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleClienteTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleEmpleadoTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleRepuestoTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.TrabajoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TrabajoDAOImp implements TrabajoDAO {
    @Autowired
    private TrabajoRepository trabajoRepository;
    @Autowired
    private DetalleClienteTrabajoDAO detalleClienteTrabajoDAO;
    @Autowired
    private DetalleEmpleadoTrabajoDAO detalleEmpleadoTrabajoDAO;
    @Autowired
    private DetalleRepuestoTrabajoDAO detalleRepuestoTrabajoDAO;

    @Override
    public Trabajo guardarDesdeDTO(TrabajoDTO dto) {
        Trabajo trabajo = new Trabajo();
        trabajo.setTipoTrabajo(TipoTrabajo.valueOf(dto.getTipoTrabajo().toString()));
        trabajo.setDiagnostico(dto.getDiagnostico());
        trabajo.setTareasRealizar(dto.getTareasRealizar());
        trabajo.setDetalles(dto.getDetalles());

        trabajoRepository.save(trabajo);
        detalleClienteTrabajoDAO.guardarDetalleClienteTrabajo(dto.getClientes(),trabajo);
        detalleEmpleadoTrabajoDAO.guardarDetalleEmpleadoTrabajo(dto.getEmpleados(),trabajo);
        detalleRepuestoTrabajoDAO.guardarDetalleRepuestoTrabajo(dto.getRepuestos(),trabajo);

        return trabajo;

    }

    @Override
    public TrabajoDetalleDTO obtenerTrabajoDetallePorId(Long id) {
        Trabajo trabajo = trabajoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Trabajo No encotrado con ID: "+id));
        TrabajoDetalleDTO dto = new TrabajoDetalleDTO();
        dto.setId(trabajo.getId());
        dto.setTipoTrabajo(trabajo.getTipoTrabajo().toString());
        dto.setDiagnostico(trabajo.getDiagnostico());
        dto.setTareasRealizar(trabajo.getTareasRealizar());
        dto.setDetalles(trabajo.getDetalles());
        dto.setEstado(trabajo.getEstado() != null ? trabajo.getEstado().toString() : null);
        dto.setFechaAlta(trabajo.getFechaAlta());
        dto.setFechaModificacion(trabajo.getFechaModificacion());

        // Convertir empleados
        List<DetalleEmpleadoDTO> empleados = trabajo.getDetallesEmpleados()
                .stream()
                .map(de -> {
                    DetalleEmpleadoDTO e = new DetalleEmpleadoDTO();
                    e.setEmpleadoId(de.getEmpleado().getId());
                    return e;
                }).toList();
        dto.setEmpleados(empleados);

        // Convertir clientes
        List<DetalleClienteDTO> clientes = trabajo.getDetallesClientes()
                .stream()
                .map(dc -> {
                    DetalleClienteDTO c = new DetalleClienteDTO();
                    c.setClienteId(dc.getCliente().getId());
                    return c;
                }).toList();
        dto.setClientes(clientes);

        // Convertir repuestos
        List<DetalleRepuestoDTO> repuestos = trabajo.getDetallesRepuestos()
                .stream()
                .map(dr -> {
                    DetalleRepuestoDTO r = new DetalleRepuestoDTO();
                    r.setRepuestoId(dr.getRepuesto().getId());
                    r.setCantidadUsada(dr.getCantidadUsada());
                    return r;
                }).toList();
        dto.setRepuestos(repuestos);

        return dto;

    }

    @Override
    public Trabajo guardar(Trabajo trabajo) {
        return trabajoRepository.save(trabajo);
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
