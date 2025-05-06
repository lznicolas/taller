package com.tallerherramientas.tallerprueba.Modelo.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_empleado_trabajo")
public class DetalleEmpleadoTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="trabajo_id")
    private Trabajo trabajo;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    public DetalleEmpleadoTrabajo() {
    }

    public DetalleEmpleadoTrabajo(Long id, Trabajo trabajo, Empleado empleado) {
        this.id = id;
        this.trabajo = trabajo;
        this.empleado = empleado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trabajo getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
