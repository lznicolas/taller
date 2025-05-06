package com.tallerherramientas.tallerprueba.Modelo.Entidades;

import com.tallerherramientas.tallerprueba.Modelo.Enums.Especialidad;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona{

    private String legajo;

    private BigDecimal sueldo;

    private Especialidad especialidad;

    @OneToMany(mappedBy = "empleado")
    private List<DetalleEmpleadoTrabajo> trabajosAsignados;

    public Empleado() {
    }

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
