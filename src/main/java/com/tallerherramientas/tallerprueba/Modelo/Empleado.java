package com.tallerherramientas.tallerprueba.Modelo;

import com.tallerherramientas.tallerprueba.Modelo.Enum.Especialidad;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona{

    private String legajo;

    private BigDecimal sueldo;

    private Especialidad especialidad;

    public Empleado(Long id, String dni, String cuilt, String nombre, String apellido, BigDecimal sueldo, Especialidad especialidad) {
        super(id, dni, cuilt, nombre, apellido);
        this.sueldo = sueldo;
        this.especialidad = especialidad;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
