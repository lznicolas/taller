package com.tallerherramientas.tallerprueba.Modelo.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_cliente_trabajo")
public class DetalleClienteTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trabajo_id")
    private Trabajo trabajo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public DetalleClienteTrabajo() {
    }

    public DetalleClienteTrabajo(Long id, Trabajo trabajo, Cliente cliente) {
        this.id = id;
        this.trabajo = trabajo;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "DetalleClienteTrabajo{" +
                "id=" + id +
                ", cliente=" + (cliente != null ? cliente.getNombre() : "null") +
                '}';
    }
}
