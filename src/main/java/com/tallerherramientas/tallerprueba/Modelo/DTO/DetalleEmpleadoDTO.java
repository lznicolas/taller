package com.tallerherramientas.tallerprueba.Modelo.DTO;

public class DetalleEmpleadoDTO {

    private Long empleadoId;
    private String observaciones;

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    //Todo lo extra que se quiera agregar del empledo debe ser aca
}
