package com.tallerherramientas.tallerprueba.Modelo.DTO;

public class DetalleClienteDTO {
    private Long clienteId;
    private String observaciones;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
