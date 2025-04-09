package com.tallerherramientas.tallerprueba.Modelo;

import com.tallerherramientas.tallerprueba.Modelo.Enum.TipoTrabajo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoTrabajo tipoTrabajo;

    private List<Empleado> empleados;

    private List<Cliente> clientes;

    private List<Repuesto> repuestos;
    private String diagnostico;//Como llego el moto/pieza/etc

    private String tareasRealizar;//Tareas a realizar

    private String detalles;//Como se entrega y cosas a tener en cuenta

    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    public Trabajo() {
    }

    public Trabajo(Long id, TipoTrabajo tipoTrabajo, List<Empleado> empleados, List<Cliente> clientes) {
        this.id = id;
        this.tipoTrabajo = tipoTrabajo;
        this.empleados = empleados;
        this.clientes = clientes;
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

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Repuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<Repuesto> repuestos) {
        this.repuestos = repuestos;
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
}
