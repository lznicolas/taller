package com.tallerherramientas.tallerprueba.Modelo.DTO;

import java.math.BigDecimal;
import java.util.List;

public class ReporteTrabajoDTO {
    private Long trabajoId;
    private Long codigoPublico;
    private String tipoTrabajo;
    private String estado;
    private String diagnostico;
    private String tareasRealizar;
    private BigDecimal costoManoDeObra;
    private String telefonoCliente;
    private String direccionCliente;
    private String responsable;
    private String cliente;
    private Long trabajoAnteriorCodigoPublico;
    private List<ReporteArticuloDTO> articulos;
    private BigDecimal totalArticulos;

    public Long getTrabajoId() {
        return trabajoId;
    }

    public void setTrabajoId(Long trabajoId) {
        this.trabajoId = trabajoId;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public BigDecimal getCostoManoDeObra() {
        return costoManoDeObra;
    }

    public void setCostoManoDeObra(BigDecimal costoManoDeObra) {
        this.costoManoDeObra = costoManoDeObra;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Long getTrabajoAnteriorCodigoPublico() {
        return trabajoAnteriorCodigoPublico;
    }

    public void setTrabajoAnteriorCodigoPublico(Long trabajoAnteriorCodigoPublico) {
        this.trabajoAnteriorCodigoPublico = trabajoAnteriorCodigoPublico;
    }

    public List<ReporteArticuloDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ReporteArticuloDTO> articulos) {
        this.articulos = articulos;
    }

    public BigDecimal getTotalArticulos() {
        return totalArticulos;
    }

    public void setTotalArticulos(BigDecimal totalArticulos) {
        this.totalArticulos = totalArticulos;
    }
}
