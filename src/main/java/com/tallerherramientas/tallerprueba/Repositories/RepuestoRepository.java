package com.tallerherramientas.tallerprueba.Repositorio;

import com.tallerherramientas.tallerprueba.Modelo.Entidades.Repuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepuestoRepository extends JpaRepository<Repuesto,Long> {
    List<Repuesto> findByTitulo(String titulo);

    @Query("SELECT r FROM Repuesto r WHERE LOWER(r.titulo) LIKE %:titulo%")
    List<Repuesto> findByAproximado(String titulo);


    Optional<Repuesto> findByCodigoDeProducto(String codigoDeProducto); // Método para buscar por código

}
