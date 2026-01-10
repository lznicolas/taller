package com.tallerherramientas.tallerprueba.Modelo.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoTrabajo {
    RECTIFICACION,
    CAMBIO_REPUESTO,
    MANTENIMIENTO;

    @JsonCreator
    public static TipoTrabajo fromString(String value) {
        if (value == null) {
            return null;
        }
        String normalized = value.toUpperCase();
        if ("RECTIFICAION".equals(normalized)) { // tolera el typo frecuente
            normalized = "RECTIFICACION";
        }
        return TipoTrabajo.valueOf(normalized);
    }
}
