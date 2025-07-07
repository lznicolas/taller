package com.tallerherramientas.tallerprueba.Services.Implementaciones.Trabajo;

import com.tallerherramientas.tallerprueba.Modelo.DTO.*;
import com.tallerherramientas.tallerprueba.Modelo.Entities.*;
import com.tallerherramientas.tallerprueba.Modelo.Enums.TipoTrabajo;
import com.tallerherramientas.tallerprueba.Repositories.*;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleClienteTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleEmpleadoTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleRepuestoTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.TrabajoDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrabajoDAOImp implements TrabajoDAO {
    @Autowired
    private final TrabajoRepository trabajoRepository;
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
    public List<Trabajo> obtenerTrabajosPorClienteId(Long clienteId) {
        List<DetalleClienteTrabajo> detalleClienteTrabajos = detalleClienteTrabajoDAO.obtenerPorClienteId(clienteId);

        System.out.println("Detalles encontrados: " + detalleClienteTrabajos);
        return detalleClienteTrabajos.stream()
                .map(DetalleClienteTrabajo::getTrabajo)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<TrabajoDTO> BuscarTrabajosPorCliente(Long clienteId) {
        List<DetalleClienteTrabajo> detalleClienteTrabajos = detalleClienteTrabajoDAO.obtenerPorClienteId(clienteId);

        return detalleClienteTrabajos.stream()
                .map(DetalleClienteTrabajo::getTrabajo)
                .distinct()
                .map(this::convertirATrabajoDTO)
                .collect(Collectors.toList());
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

    private TrabajoDTO convertirATrabajoDTO(Trabajo trabajo){
        TrabajoDTO dto = new TrabajoDTO();
        dto.setTipoTrabajo(trabajo.getTipoTrabajo());
        dto.setDiagnostico(trabajo.getDiagnostico());
        dto.setTareasRealizar(trabajo.getTareasRealizar());
        dto.setDetalles(trabajo.getDetalles());

        //Empleados
        List<DetalleEmpleadoDTO> empleadoDTOS = trabajo.getDetallesEmpleados().stream()
                .map(detalleEmpleadoTrabajo -> {
                    DetalleEmpleadoDTO empleadoDTO = new DetalleEmpleadoDTO();
                    empleadoDTO.setEmpleadoId(detalleEmpleadoTrabajo.getEmpleado().getId());
                    return empleadoDTO;
                }).collect(Collectors.toList());
        dto.setEmpleados(empleadoDTOS);

        //repuestos
        List<DetalleRepuestoDTO> repuestoDTOS = trabajo.getDetallesRepuestos().stream()
                .map(detalleRepuestoTrabajo -> {
                    DetalleRepuestoDTO detalleRepuestoDTO = new DetalleRepuestoDTO();
                    detalleRepuestoDTO.setRepuestoId(detalleRepuestoTrabajo.getRepuesto().getId());
                    detalleRepuestoDTO.setCantidadUsada(detalleRepuestoTrabajo.getCantidadUsada());

                    return detalleRepuestoDTO;
                }).collect(Collectors.toList());
        dto.setRepuestos(repuestoDTOS);

        //clientes
        List<DetalleClienteDTO>clienteDTOS = trabajo.getDetallesClientes().stream()
                .map(detalleClienteTrabajo -> {
                    DetalleClienteDTO detalleClienteDTO = new DetalleClienteDTO();
                    detalleClienteDTO.setClienteId(detalleClienteTrabajo.getId());
                    return detalleClienteDTO;
                }).collect(Collectors.toList());
        dto.setClientes(clienteDTOS);
        return dto;
    }
}
