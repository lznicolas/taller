package com.tallerherramientas.tallerprueba.Modelo.Entidades;

import jakarta.persistence.*;

@Entity
public class DetalleRepuestoTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Trabajo trabajo;

    @ManyToOne
    private Repuesto repuesto;

    private Integer cantidadUsada;

    public DetalleRepuestoTrabajo() {
    }

    public DetalleRepuestoTrabajo(Long id, Trabajo trabajo, Repuesto repuesto, Integer cantidadUsada) {
        this.id = id;
        this.trabajo = trabajo;
        this.repuesto = repuesto;
        this.cantidadUsada = cantidadUsada;
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

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public Integer getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(Integer cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }

    @Override
    public String toString() {
        return "DetalleRepuestoTrabajo{" +
                ", repuesto=" + (repuesto != null ? repuesto.getTitulo(): "null" )+
                ", cantidadUsada=" + cantidadUsada +
                '}';
    }
}
