package com.tallerherramientas.tallerprueba.Modelo.Entities;

import jakarta.persistence.*;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "articulo_id", nullable = false,unique = true)
    private Articulo articulo;
    private Integer cantidad;
    public Stock(){}

    public Stock(Long id,Articulo articulo, Integer cantidad){
        this.id = id;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public Stock(Articulo articulo, Integer cantidad) {
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
