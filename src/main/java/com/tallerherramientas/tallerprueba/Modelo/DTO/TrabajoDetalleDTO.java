package com.tallerherramientas.tallerprueba.Modelo.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleArticuloDTO;

public class TrabajoDetalleDTO {
    private Long id;
    private Long codigoPublico;
    private String tipoTrabajo;
    private String diagnostico;
    private String tareasRealizar;
    private String detalles;
    private BigDecimal costoManoDeObra;
    private String estado;
    private Long trabajoAnteriorId;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaModificacion;

    private List<DetalleEmpleadoDTO> empleados;
    private List<DetalleClienteDTO> clientes;
    private List<DetalleArticuloDTO> articulos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoPublico() {
        return codigoPublico;
    }

    public void setCodigoPublico(Long codigoPublico) {
        this.codigoPublico = codigoPublico;
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

    public BigDecimal getCostoManoDeObra() {
        return costoManoDeObra;
    }

    public void setCostoManoDeObra(BigDecimal costoManoDeObra) {
        this.costoManoDeObra = costoManoDeObra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getTrabajoAnteriorId() {
        return trabajoAnteriorId;
    }

    public void setTrabajoAnteriorId(Long trabajoAnteriorId) {
        this.trabajoAnteriorId = trabajoAnteriorId;
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

    public List<DetalleArticuloDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<DetalleArticuloDTO> articulos) {
        this.articulos = articulos;
    }
}
