package com.tallerherramientas.tallerprueba.Modelo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "detalle_articulo_trabajo")
public class DetalleArticuloTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore // previene ciclos al serializar trabajos/articulos
    private Trabajo trabajo;

    @ManyToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;

    private Integer cantidadUsada;

    public DetalleArticuloTrabajo() {
    }

    public DetalleArticuloTrabajo(Long id, Trabajo trabajo, Articulo articulo, Integer cantidadUsada) {
        this.id = id;
        this.trabajo = trabajo;
        this.articulo = articulo;
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

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Integer getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(Integer cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }

    @Override
    public String toString() {
        return "DetalleArticuloTrabajo{" +
                ", articulo=" + (articulo != null ? articulo.getTitulo(): "null" )+
                ", cantidadUsada=" + cantidadUsada +
                '}';
    }
}
