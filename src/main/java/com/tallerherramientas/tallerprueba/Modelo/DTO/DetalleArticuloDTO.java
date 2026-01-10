package com.tallerherramientas.tallerprueba.Modelo.DTO;

public class DetalleArticuloDTO {
    private Long articuloId;
    private Integer cantidadUsada;

    public Long getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Long articuloId) {
        this.articuloId = articuloId;
    }

    public Integer getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(Integer cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }
}
