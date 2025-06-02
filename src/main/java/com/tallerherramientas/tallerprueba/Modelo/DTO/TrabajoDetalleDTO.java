package com.tallerherramientas.tallerprueba.Modelo.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class TrabajoDetalleDTO {
    private Long id;
    private String tipoTrabajo;
    private String diagnostico;
    private String tareasRealizar;
    private String detalles;
    private String estado;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaModificacion;

    private List<DetalleEmpleadoDTO> empleados;
    private List<DetalleClienteDTO> clientes;
    private List<DetalleRepuestoDTO> repuestos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTareasRealizar() {
        return tareasRealizar;
    }

    public void setTareasRealizar(String tareasRealizar) {
        this.tareasRealizar = tareasRealizar;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<DetalleEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<DetalleEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public List<DetalleClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<DetalleClienteDTO> clientes) {
        this.clientes = clientes;
    }

    public List<DetalleRepuestoDTO> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<DetalleRepuestoDTO> repuestos) {
        this.repuestos = repuestos;
    }
}
