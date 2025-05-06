package com.tallerherramientas.tallerprueba.Modelo.Entities;

import jakarta.persistence.*;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "repuesto_id", nullable = false,unique = true)
    private Repuesto repuesto;
    private Integer cantidad;
    public Stock(){}

    public Stock(Long id,Repuesto repuesto, Integer cantidad){
        this.id = id;
        this.repuesto = repuesto;
        this.cantidad = cantidad;
    }

    public Stock(Repuesto repuesto, Integer cantidad) {
        this.repuesto = repuesto;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
