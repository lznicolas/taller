package com.tallerherramientas.tallerprueba.Modelo;

import com.tallerherramientas.tallerprueba.Modelo.Enum.TipoCliente;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Persona{
    private TipoCliente tipoCliente;

    private String observaciones;

    private Double limiteCredito;

    private Double saldo;

    public Cliente(Long id, String dni, String cuilt, String nombre, String apellido, TipoCliente tipoCliente) {
        super(id, dni, cuilt, nombre, apellido);
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
