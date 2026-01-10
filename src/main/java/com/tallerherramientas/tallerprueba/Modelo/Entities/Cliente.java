package com.tallerherramientas.tallerprueba.Modelo.Entities;

import com.tallerherramientas.tallerprueba.Modelo.Enums.TipoCliente;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Generated;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "persona_id")
@SuperBuilder
public class Cliente extends Persona{
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    private String observaciones;

    private Double limiteCredito;

    private Double saldo;

    /*@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trabajo> trabajos;*/

    public Cliente() {
    }

    public Cliente(Long id, String dni, String cuil, String nombre, String apellido, TipoCliente tipoCliente) {
        super(id, dni, cuil, nombre, apellido);
        this.tipoCliente = tipoCliente;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}
