package com.tallerherramientas.tallerprueba.Modelo.DTO;

public class DetalleRepuestoDTO {
    private Long repuestoId;
    private Integer cantidadUsada;

    public Long getRepuestoId() {
        return repuestoId;
    }

    public void setRepuestoId(Long repuestoId) {
        this.repuestoId = repuestoId;
    }

    public Integer getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(Integer cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }
}
