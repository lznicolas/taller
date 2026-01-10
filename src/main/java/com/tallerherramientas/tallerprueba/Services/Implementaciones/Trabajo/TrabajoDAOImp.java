package com.tallerherramientas.tallerprueba.Services.Implementaciones.Trabajo;

import com.tallerherramientas.tallerprueba.Modelo.DTO.*;
import com.tallerherramientas.tallerprueba.Modelo.Entities.*;
import com.tallerherramientas.tallerprueba.Modelo.Enums.TipoTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Enums.EstadoTrabajo;
import com.tallerherramientas.tallerprueba.Repositories.*;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleClienteTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleEmpleadoTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleArticuloTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.TrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.StockDAO;
import com.tallerherramientas.tallerprueba.Repositories.DetalleArticuloTrabajoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private DetalleArticuloTrabajoDAO detalleArticuloTrabajoDAO;
    @Autowired
    private StockDAO stockDAO;
    @Autowired
    private DetalleArticuloTrabajoRepository detalleArticuloTrabajoRepository;
    @Autowired
    private DetalleEmpleadoTrabajoRepository detalleEmpleadoTrabajoRepository;
    @Autowired
    private DetalleClienteTrabajoRepository detalleClienteTrabajoRepository;

    @Override
    public Trabajo guardarDesdeDTO(TrabajoDTO dto) {
        validarRequisitos(dto);
        Trabajo trabajo = new Trabajo();
        trabajo.setCodigoPublico(generarSiguienteCodigo());
        trabajo.setTipoTrabajo(obtenerTipoTrabajo(dto.getTipoTrabajo()));
        trabajo.setDiagnostico(dto.getDiagnostico());
        trabajo.setTareasRealizar(dto.getTareasRealizar());
        trabajo.setCostoManoDeObra(dto.getCostoManoDeObra());
        trabajo.setDetalles(dto.getDetalles());
        trabajo.setEstado(obtenerEstado(dto.getEstado(), EstadoTrabajo.PENDIENTE));
        trabajo.setTrabajoAnterior(resolverTrabajoAnterior(dto.getTrabajoAnteriorId(), dto.getClientes()));

        trabajoRepository.save(trabajo);
        detalleClienteTrabajoDAO.guardarDetalleClienteTrabajo(obtenerClientes(dto),trabajo);
        detalleEmpleadoTrabajoDAO.guardarDetalleEmpleadoTrabajo(obtenerEmpleados(dto),trabajo);
        detalleArticuloTrabajoDAO.guardarDetalleArticuloTrabajo(obtenerArticulos(dto),trabajo);
        trabajo.setDetallesArticulos(detalleArticuloTrabajoRepository.findByTrabajoId(trabajo.getId()));

        return trabajo;
    }

    @Override
    public Trabajo actualizarDesdeDTO(Long id, TrabajoDTO dto) {
        validarRequisitos(dto);
        Trabajo trabajo = trabajoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajo no encontrado con ID: " + id));

        if (trabajo.getCodigoPublico() == null) {
            trabajo.setCodigoPublico(generarSiguienteCodigo());
        }
        trabajo.setTipoTrabajo(obtenerTipoTrabajo(dto.getTipoTrabajo()));
        trabajo.setDiagnostico(dto.getDiagnostico());
        trabajo.setTareasRealizar(dto.getTareasRealizar());
        trabajo.setCostoManoDeObra(dto.getCostoManoDeObra());
        trabajo.setDetalles(dto.getDetalles());
        if (dto.getEstado() != null) {
            trabajo.setEstado(obtenerEstado(dto.getEstado(), trabajo.getEstado()));
        }
        if (dto.getTrabajoAnteriorId() != null) {
            trabajo.setTrabajoAnterior(resolverTrabajoAnterior(dto.getTrabajoAnteriorId(), dto.getClientes()));
        }

        if (trabajo.getDetallesClientes() != null) {
            trabajo.getDetallesClientes().clear();
        }
        if (trabajo.getDetallesEmpleados() != null) {
            trabajo.getDetallesEmpleados().clear();
        }
        if (trabajo.getDetallesArticulos() != null) {
            // Reponemos stock antes de limpiar detalles
            trabajo.getDetallesArticulos().forEach(detalle -> {
                Stock stock = stockDAO.obtenerPorId(detalle.getArticulo().getId()).orElse(new Stock(detalle.getArticulo(), 0));
                int disponible = stock.getCantidad() != null ? stock.getCantidad() : 0;
                stock.setCantidad(disponible + (detalle.getCantidadUsada() != null ? detalle.getCantidadUsada() : 0));
                stockDAO.guardar(stock);
            });
            trabajo.getDetallesArticulos().clear();
        }

        trabajoRepository.save(trabajo);

        detalleClienteTrabajoDAO.guardarDetalleClienteTrabajo(obtenerClientes(dto), trabajo);
        detalleEmpleadoTrabajoDAO.guardarDetalleEmpleadoTrabajo(obtenerEmpleados(dto), trabajo);
        detalleArticuloTrabajoDAO.guardarDetalleArticuloTrabajo(obtenerArticulos(dto), trabajo);
        trabajo.setDetallesArticulos(detalleArticuloTrabajoRepository.findByTrabajoId(trabajo.getId()));
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
        dto.setCostoManoDeObra(trabajo.getCostoManoDeObra());
        dto.setEstado(trabajo.getEstado() != null ? trabajo.getEstado().toString() : null);
        dto.setCodigoPublico(trabajo.getCodigoPublico());
        dto.setTrabajoAnteriorId(trabajo.getTrabajoAnterior() != null ? trabajo.getTrabajoAnterior().getId() : null);
        dto.setFechaAlta(trabajo.getFechaAlta());
        dto.setFechaModificacion(trabajo.getFechaModificacion());

        // Convertir empleados
        List<DetalleEmpleadoDTO> empleados = trabajo.getDetallesEmpleados() != null
                ? trabajo.getDetallesEmpleados()
                    .stream()
                    .map(de -> {
                        DetalleEmpleadoDTO e = new DetalleEmpleadoDTO();
                        e.setEmpleadoId(de.getEmpleado().getId());
                        return e;
                    }).toList()
                : List.of();
        dto.setEmpleados(empleados);

        // Convertir clientes
        List<DetalleClienteDTO> clientes = trabajo.getDetallesClientes() != null
                ? trabajo.getDetallesClientes()
                    .stream()
                    .map(dc -> {
                        DetalleClienteDTO c = new DetalleClienteDTO();
                        c.setClienteId(dc.getCliente().getId());
                        return c;
                    }).toList()
                : List.of();
        dto.setClientes(clientes);

        // Convertir articulos
        List<DetalleArticuloDTO> articulos = obtenerDetallesArticulos(trabajo)
                .stream()
                .map(dr -> {
                    DetalleArticuloDTO r = new DetalleArticuloDTO();
                    r.setArticuloId(dr.getArticulo().getId());
                    r.setCantidadUsada(dr.getCantidadUsada());
                    return r;
                }).toList();
        dto.setArticulos(articulos);

        return dto;

    }

    @Override
    public List<TrabajoDetalleDTO> listarDetalles() {
        List<Trabajo> trabajos = trabajoRepository.findAll();
        return trabajos.stream()
                .map(this::convertirDetalle)
                .collect(Collectors.toList());
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
    public ReporteTrabajoDTO generarReporte(Long trabajoId) {
        Trabajo trabajo = trabajoRepository.findById(trabajoId)
                .orElseThrow(() -> new RuntimeException("Trabajo no encontrado con ID: " + trabajoId));

        List<DetalleEmpleadoTrabajo> empleados = detalleEmpleadoTrabajoRepository.findByTrabajoId(trabajoId);
        List<DetalleClienteTrabajo> clientes = detalleClienteTrabajoRepository.findByTrabajoId(trabajoId);
        List<DetalleArticuloTrabajo> detallesArticulos = detalleArticuloTrabajoRepository.findByTrabajoId(trabajoId);

        ReporteTrabajoDTO reporte = new ReporteTrabajoDTO();
        reporte.setTrabajoId(trabajo.getId());
        reporte.setCodigoPublico(trabajo.getCodigoPublico());
        reporte.setTipoTrabajo(trabajo.getTipoTrabajo() != null ? trabajo.getTipoTrabajo().toString() : null);
        reporte.setDiagnostico(trabajo.getDiagnostico());
        reporte.setTareasRealizar(trabajo.getTareasRealizar());
        reporte.setCostoManoDeObra(trabajo.getCostoManoDeObra());
        reporte.setTrabajoAnteriorCodigoPublico(
                trabajo.getTrabajoAnterior() != null ? trabajo.getTrabajoAnterior().getCodigoPublico() : null
        );
        reporte.setResponsable(formatearPersonas(
                empleados.stream()
                        .map(DetalleEmpleadoTrabajo::getEmpleado)
                        .toList()
        ));
        reporte.setCliente(formatearPersonas(
                clientes.stream()
                        .map(DetalleClienteTrabajo::getCliente)
                        .toList()
        ));

        List<ReporteArticuloDTO> articulos = detallesArticulos.stream()
                .map(detalle -> {
                    Articulo articulo = detalle.getArticulo();
                    BigDecimal precioUnitario = articulo != null && articulo.getPrecioUnitario() != null
                            ? articulo.getPrecioUnitario()
                            : BigDecimal.ZERO;
                    Integer cantidad = detalle.getCantidadUsada() != null ? detalle.getCantidadUsada() : 0;
                    BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));

                    ReporteArticuloDTO dto = new ReporteArticuloDTO();
                    dto.setArticuloId(articulo != null ? articulo.getId() : null);
                    dto.setTitulo(articulo != null ? articulo.getTitulo() : null);
                    dto.setCodigoDeProducto(articulo != null ? articulo.getCodigoDeProducto() : null);
                    dto.setPrecioUnitario(precioUnitario);
                    dto.setCantidad(cantidad);
                    dto.setSubtotal(subtotal);
                    return dto;
                })
                .toList();

        BigDecimal total = articulos.stream()
                .map(ReporteArticuloDTO::getSubtotal)
                .filter(value -> value != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        reporte.setArticulos(articulos);
        reporte.setTotalArticulos(total);
        return reporte;
    }

    @Override
    public Trabajo actualizarCostoManoDeObra(Long trabajoId, BigDecimal costoManoDeObra) {
        Trabajo trabajo = trabajoRepository.findById(trabajoId)
                .orElseThrow(() -> new RuntimeException("Trabajo no encontrado con ID: " + trabajoId));
        trabajo.setCostoManoDeObra(costoManoDeObra);
        return trabajoRepository.save(trabajo);
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
        dto.setId(trabajo.getId());
        dto.setCodigoPublico(trabajo.getCodigoPublico());
        dto.setTipoTrabajo(trabajo.getTipoTrabajo());
        dto.setDiagnostico(trabajo.getDiagnostico());
        dto.setTareasRealizar(trabajo.getTareasRealizar());
        dto.setDetalles(trabajo.getDetalles());
        dto.setCostoManoDeObra(trabajo.getCostoManoDeObra());
        dto.setEstado(trabajo.getEstado() != null ? trabajo.getEstado().toString() : null);
        dto.setTrabajoAnteriorId(trabajo.getTrabajoAnterior() != null ? trabajo.getTrabajoAnterior().getId() : null);

        //Empleados
        List<DetalleEmpleadoDTO> empleadoDTOS = trabajo.getDetallesEmpleados() != null
                ? trabajo.getDetallesEmpleados().stream()
                    .map(detalleEmpleadoTrabajo -> {
                        DetalleEmpleadoDTO empleadoDTO = new DetalleEmpleadoDTO();
                        empleadoDTO.setEmpleadoId(detalleEmpleadoTrabajo.getEmpleado().getId());
                        return empleadoDTO;
                    }).collect(Collectors.toList())
                : List.of();
        dto.setEmpleados(empleadoDTOS);

        //articulos
        List<DetalleArticuloDTO> articuloDTOS = obtenerDetallesArticulos(trabajo).stream()
                .map(detalleArticuloTrabajo -> {
                    DetalleArticuloDTO detalleArticuloDTO = new DetalleArticuloDTO();
                    detalleArticuloDTO.setArticuloId(detalleArticuloTrabajo.getArticulo().getId());
                    detalleArticuloDTO.setCantidadUsada(detalleArticuloTrabajo.getCantidadUsada());

                    return detalleArticuloDTO;
                }).collect(Collectors.toList());
        dto.setArticulos(articuloDTOS);

        //clientes
        List<DetalleClienteDTO>clienteDTOS = trabajo.getDetallesClientes() != null
                ? trabajo.getDetallesClientes().stream()
                    .map(detalleClienteTrabajo -> {
                        DetalleClienteDTO detalleClienteDTO = new DetalleClienteDTO();
                        detalleClienteDTO.setClienteId(detalleClienteTrabajo.getCliente().getId());
                        return detalleClienteDTO;
                    }).collect(Collectors.toList())
                : List.of();
        dto.setClientes(clienteDTOS);
        return dto;
    }

    private TrabajoDetalleDTO convertirDetalle(Trabajo trabajo) {
        TrabajoDetalleDTO dto = new TrabajoDetalleDTO();
        dto.setId(trabajo.getId());
        dto.setTipoTrabajo(trabajo.getTipoTrabajo().toString());
        dto.setDiagnostico(trabajo.getDiagnostico());
        dto.setTareasRealizar(trabajo.getTareasRealizar());
        dto.setDetalles(trabajo.getDetalles());
        dto.setCostoManoDeObra(trabajo.getCostoManoDeObra());
        dto.setEstado(trabajo.getEstado() != null ? trabajo.getEstado().toString() : null);
        dto.setCodigoPublico(trabajo.getCodigoPublico());
        dto.setTrabajoAnteriorId(trabajo.getTrabajoAnterior() != null ? trabajo.getTrabajoAnterior().getId() : null);
        dto.setFechaAlta(trabajo.getFechaAlta());
        dto.setFechaModificacion(trabajo.getFechaModificacion());

        List<DetalleEmpleadoDTO> empleados = trabajo.getDetallesEmpleados() != null ? trabajo.getDetallesEmpleados()
                .stream()
                .map(de -> {
                    DetalleEmpleadoDTO e = new DetalleEmpleadoDTO();
                    e.setEmpleadoId(de.getEmpleado().getId());
                    return e;
                }).toList() : List.of();
        dto.setEmpleados(empleados);

        List<DetalleClienteDTO> clientes = trabajo.getDetallesClientes() != null ? trabajo.getDetallesClientes()
                .stream()
                .map(dc -> {
                    DetalleClienteDTO c = new DetalleClienteDTO();
                    c.setClienteId(dc.getCliente().getId());
                    return c;
                }).toList() : List.of();
        dto.setClientes(clientes);

        List<DetalleArticuloDTO> articulos = obtenerDetallesArticulos(trabajo) != null ? obtenerDetallesArticulos(trabajo)
                .stream()
                .map(dr -> {
                    DetalleArticuloDTO r = new DetalleArticuloDTO();
                    r.setArticuloId(dr.getArticulo().getId());
                    r.setCantidadUsada(dr.getCantidadUsada());
                    return r;
                }).toList() : List.of();
        dto.setArticulos(articulos);

        return dto;
    }

    private List<DetalleClienteDTO> obtenerClientes(TrabajoDTO dto){
        return dto.getClientes() != null ? dto.getClientes() : List.of();
    }

    private List<DetalleEmpleadoDTO> obtenerEmpleados(TrabajoDTO dto){
        return dto.getEmpleados() != null ? dto.getEmpleados() : List.of();
    }

    private List<DetalleArticuloDTO> obtenerArticulos(TrabajoDTO dto){
        return dto.getArticulos() != null ? dto.getArticulos() : List.of();
    }

    private List<DetalleArticuloTrabajo> obtenerDetallesArticulos(Trabajo trabajo){
        if (trabajo == null) {
            return List.of();
        }
        List<DetalleArticuloTrabajo> detalles = trabajo.getDetallesArticulos();
        if (detalles == null || detalles.isEmpty()) {
            return detalleArticuloTrabajoRepository.findByTrabajoId(trabajo.getId());
        }
        return detalles;
    }

    private Long generarSiguienteCodigo() {
        Trabajo ultimo = trabajoRepository.findTopByOrderByCodigoPublicoDesc();
        if (ultimo == null || ultimo.getCodigoPublico() == null || ultimo.getCodigoPublico() < 10000) {
            return 10000L;
        }
        return ultimo.getCodigoPublico() + 1;
    }

    private Trabajo resolverTrabajoAnterior(Long trabajoAnteriorId, List<DetalleClienteDTO> clientes) {
        if (trabajoAnteriorId == null) {
            return null;
        }
        Trabajo previo = trabajoRepository.findById(trabajoAnteriorId)
                .orElseThrow(() -> new RuntimeException("Trabajo anterior no encontrado con ID: " + trabajoAnteriorId));
        if (previo.getEstado() != EstadoTrabajo.TERMINADO) {
            throw new RuntimeException("Solo se pueden vincular trabajos en estado TERMINADO");
        }
        if (clientes != null && !clientes.isEmpty() && previo.getDetallesClientes() != null) {
            boolean comparteCliente = previo.getDetallesClientes().stream()
                    .anyMatch(dc -> clientes.stream().anyMatch(c -> c.getClienteId().equals(dc.getCliente().getId())));
            if (!comparteCliente) {
                throw new RuntimeException("El trabajo anterior no pertenece al cliente seleccionado");
            }
        }
        return previo;
    }

    private void validarRequisitos(TrabajoDTO dto) {
        if (dto.getClientes() == null || dto.getClientes().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar al menos un cliente para el trabajo.");
        }
        if (dto.getEmpleados() == null || dto.getEmpleados().isEmpty()) {
            throw new IllegalArgumentException("Debe indicar al menos un empleado responsable para el trabajo.");
        }
        if (dto.getTipoTrabajo() == null) {
            throw new IllegalArgumentException("El tipo de trabajo es obligatorio.");
        }
    }

    private TipoTrabajo obtenerTipoTrabajo(TipoTrabajo tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de trabajo inválido.");
        }
        return tipo;
    }

    private EstadoTrabajo obtenerEstado(String estadoStr, EstadoTrabajo porDefecto) {
        if (estadoStr == null || estadoStr.isBlank()) {
            return porDefecto;
        }
        try {
            return EstadoTrabajo.valueOf(estadoStr.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Estado de trabajo inválido: " + estadoStr, ex);
        }
    }

    private String formatearPersonas(List<? extends Persona> personas) {
        if (personas == null || personas.isEmpty()) {
            return "-";
        }
        return personas.stream()
                .map(persona -> String.format("%s %s", persona.getNombre(), persona.getApellido()).trim())
                .collect(Collectors.joining(", "));
    }
}
