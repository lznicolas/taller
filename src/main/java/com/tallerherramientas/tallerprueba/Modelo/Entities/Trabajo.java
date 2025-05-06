package com.tallerherramientas.tallerprueba.Modelo.Entities;

import com.tallerherramientas.tallerprueba.Modelo.Enums.EstadoTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Enums.TipoTrabajo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trabajos")
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoTrabajo tipoTrabajo;

    @OneToMany(mappedBy = "trabajo",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleEmpleadoTrabajo> detallesEmpleados;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    @OneToMany(mappedBy = "trabajo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleRepuestoTrabajo> detallesRepuestos;
    private String diagnostico;//Como llego el motor/pieza/etc

    private String tareasRealizar;//Tareas a realizar

    private String detalles;//Como se entrega y cosas a tener en cuenta

    private EstadoTrabajo estado;

    //duda
    @OneToMany(mappedBy = "trabajo",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DetalleClienteTrabajo> detallesClientes;

    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    public Trabajo() {
    }

    public Trabajo(Long id, TipoTrabajo tipoTrabajo, List<DetalleEmpleadoTrabajo> detallesEmpleados, Cliente cliente) {
        this.id = id;
        this.tipoTrabajo = tipoTrabajo;
        this.detallesEmpleados = detallesEmpleados;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTrabajo getTipoTrabajo() {
        return tipoTrabajo;
    }

    public void setTipoTrabajo(TipoTrabajo tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    public List<DetalleEmpleadoTrabajo> getDetallesEmpleados() {
        return detallesEmpleados;
    }

    public void setDetallesEmpleados(List<DetalleEmpleadoTrabajo> detallesEmpleados) {
        this.detallesEmpleados = detallesEmpleados;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTareasRealizar() {
        return tareasRealizar;
    }

    public void setTareasRealizar(String tareasRealizar) {
        this.tareasRealizar = tareasRealizar;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @PrePersist
    private void antesDePersistir(){
        this.fechaAlta = LocalDateTime.now();
    }

    @PreUpdate
    private void antesDeUpdate(){
        this.fechaModificacion = LocalDateTime.now();
    }

    public List<DetalleRepuestoTrabajo> getDetallesRepuestos() {
        return detallesRepuestos;
    }

    public void setDetallesRepuestos(List<DetalleRepuestoTrabajo> detallesRepuestos) {
        this.detallesRepuestos = detallesRepuestos;
    }

    public EstadoTrabajo getEstado() {
        return estado;
    }

    public void setEstado(EstadoTrabajo estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<DetalleClienteTrabajo> getDetallesClientes() {
        return detallesClientes;
    }

    public void setDetallesClientes(List<DetalleClienteTrabajo> detallesClientes) {
        this.detallesClientes = detallesClientes;
    }

    @Override
    public String toString() {
        return "Trabajo{" +
                "id=" + id +
                ", tipoTrabajo=" + tipoTrabajo +
                ", diagnostico='" + diagnostico + '\'' +
                ", tareasRealizar='" + tareasRealizar + '\'' +
                ", detalles='" + detalles + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaModificacion=" + fechaModificacion +
                ", detallesRepuestos=" + (detallesRepuestos != null ? detallesRepuestos.size() + " repuestos" : "sin repuestos") +
                ", detallesEmpleados=" + (detallesEmpleados != null ? detallesEmpleados.size() + " empleados" : "sin empleados") +
                ", detallesClientes=" + (detallesClientes != null ? detallesClientes.size() + " clientes" : "sin clientes") +
                '}';
    }
}
