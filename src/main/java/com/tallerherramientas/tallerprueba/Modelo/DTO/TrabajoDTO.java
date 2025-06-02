package com.tallerherramientas.tallerprueba.Modelo.DTO;

import com.tallerherramientas.tallerprueba.Modelo.Enums.TipoTrabajo;

import java.util.List;

public class TrabajoDTO {
    private TipoTrabajo tipoTrabajo;
    private String diagnostico;
    private String tareasRealizar;
    private String detalles;

    private List<DetalleEmpleadoDTO> empleados;
    private List<DetalleRepuestoDTO> repuestos;
    private List<DetalleClienteDTO> clientes;

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

    public List<DetalleEmpleadoDTO> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<DetalleEmpleadoDTO> empleados) {
        this.empleados = empleados;
    }

    public List<DetalleRepuestoDTO> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<DetalleRepuestoDTO> repuestos) {
        this.repuestos = repuestos;
    }

    public List<DetalleClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<DetalleClienteDTO> clientes) {
        this.clientes = clientes;
    }
}
