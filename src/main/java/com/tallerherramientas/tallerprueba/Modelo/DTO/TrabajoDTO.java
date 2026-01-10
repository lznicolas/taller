package com.tallerherramientas.tallerprueba.Modelo.DTO;

import com.tallerherramientas.tallerprueba.Modelo.Enums.TipoTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleArticuloDTO;

import java.math.BigDecimal;
import java.util.List;

public class TrabajoDTO {
    private Long id;
    private Long codigoPublico;
    private TipoTrabajo tipoTrabajo;
    private String diagnostico;
    private String tareasRealizar;
    private String detalles;
    private BigDecimal costoManoDeObra;
    private String estado;
    private Long trabajoAnteriorId;

    private List<DetalleEmpleadoDTO> empleados;
    private List<DetalleArticuloDTO> articulos;
    private List<DetalleClienteDTO> clientes;

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

    public TipoTrabajo getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(TipoTrabajo tipoTrabajo) {
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

    public List<DetalleEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<DetalleEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public List<DetalleArticuloDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<DetalleArticuloDTO> articulos) {
        this.articulos = articulos;
    }

    public List<DetalleClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<DetalleClienteDTO> clientes) {
        this.clientes = clientes;
    }
}
