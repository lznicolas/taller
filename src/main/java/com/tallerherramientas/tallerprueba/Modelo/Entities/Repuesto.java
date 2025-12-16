package com.tallerherramientas.tallerprueba.Modelo.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tallerherramientas.tallerprueba.Modelo.Enums.Ubicacion;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Repuesto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @JsonProperty("codigoDeProducto")
        @Column(unique = true, nullable = false)
        private String codigoDeProducto;
        @JsonProperty("titulo")
        private String titulo;
        @JsonProperty("descripcion")
        @Column(columnDefinition = "TEXT") // Usa TEXT para cadenas largas
        private String descripcion;
        @JsonProperty("ubicacion")
        @Enumerated(EnumType.STRING)
        private Ubicacion ubicacion;

        @OneToMany(mappedBy = "repuesto")
        @JsonIgnore // evitamos recursividad al serializar repuestos
        private List<DetalleRepuestoTrabajo> detalllesTrabajo;
        public Repuesto() {
        }

        public Repuesto(Long id, String codigoDeProducto, String titulo) {
                this.id = id;
                this.codigoDeProducto = codigoDeProducto;
                this.titulo = titulo;
        }

        //GettersSetters
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getCodigoDeProducto() {
                return codigoDeProducto;
        }

        public void setCodigoDeProducto(String codigoDeProducto) {
                this.codigoDeProducto = codigoDeProducto;
        }

        public String getTitulo() {
                return titulo;
        }

        public void setTitulo(String titulo) {
                this.titulo = titulo;
        }

        public String getDescripcion() {
                return descripcion;
        }

        public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
        }

        public Ubicacion getUbicacion() {
                return ubicacion;
        }

        public void setUbicacion(Ubicacion ubicacion) {
                this.ubicacion = ubicacion;
        }

        public List<DetalleRepuestoTrabajo> getDetalllesTrabajo() {
                return detalllesTrabajo;
        }

        public void setDetalllesTrabajo(List<DetalleRepuestoTrabajo> detalllesTrabajo) {
                this.detalllesTrabajo = detalllesTrabajo;
        }

        @Override
        public String toString() {
                return "Repuesto{" +
                        "id=" + id +
                        ", Codigo de producto='" + codigoDeProducto + '\'' +
                        ", titulo='" + titulo + '\'' +
                        ", descripcion='" + descripcion + '\'' +
                        ", ubicacion=" + ubicacion +
                        ", detalllesTrabajo=" + detalllesTrabajo +
                        '}';
        }
}
