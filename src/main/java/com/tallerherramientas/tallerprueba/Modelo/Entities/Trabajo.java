package com.tallerherramientas.tallerprueba.Modelo.Entities;

import com.tallerherramientas.tallerprueba.Modelo.Enums.EstadoTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Enums.TipoTrabajo;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "trabajos")
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_publico", unique = true, nullable = false)
    private Long codigoPublico;
    @Enumerated(EnumType.STRING)
    private TipoTrabajo tipoTrabajo;
    @Enumerated(EnumType.STRING)
    private EstadoTrabajo estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajo_anterior_id")
    private Trabajo trabajoAnterior;

    @OneToMany(mappedBy = "trabajo",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleEmpleadoTrabajo> detallesEmpleados;

    /*@ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;*/
    @OneToMany(mappedBy = "trabajo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleArticuloTrabajo> detallesArticulos;
    private String diagnostico;//Como llego el motor/pieza/etc

    private String tareasRealizar;//Tareas a realizar

    @Column(name = "costo_mano_obra", precision = 12, scale = 2)
    private BigDecimal costoManoDeObra;

    private String detalles;//Como se entrega y cosas a tener en cuenta

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

    public Long getCodigoPublico() {
        return codigoPublico;
    }

    public void setCodigoPublico(Long codigoPublico) {
        this.codigoPublico = codigoPublico;
    }

    public Trabajo getTrabajoAnterior() {
        return trabajoAnterior;
    }

    public void setTrabajoAnterior(Trabajo trabajoAnterior) {
        this.trabajoAnterior = trabajoAnterior;
    }

    public List<DetalleEmpleadoTrabajo> getDetallesEmpleados() {
        return detallesEmpleados;
    }

    public void setDetallesEmpleados(List<DetalleEmpleadoTrabajo> detallesEmpleados) {
        this.detallesEmpleados = detallesEmpleados;
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

    public BigDecimal getCostoManoDeObra() {
        return costoManoDeObra;
    }

    public void setCostoManoDeObra(BigDecimal costoManoDeObra) {
        this.costoManoDeObra = costoManoDeObra;
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

    public List<DetalleArticuloTrabajo> getDetallesArticulos() {
        return detallesArticulos;
    }

    public void setDetallesArticulos(List<DetalleArticuloTrabajo> detallesArticulos) {
        this.detallesArticulos = detallesArticulos;
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
                ", costoManoDeObra=" + costoManoDeObra +
                ", detalles='" + detalles + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaModificacion=" + fechaModificacion +
                ", detallesArticulos=" + (detallesArticulos != null ? detallesArticulos.size() + " articulos" : "sin articulos") +
                ", detallesEmpleados=" + (detallesEmpleados != null ? detallesEmpleados.size() + " empleados" : "sin empleados") +
                ", detallesClientes=" + (detallesClientes != null ? detallesClientes.size() + " clientes" : "sin clientes") +
                '}';
    }
}
