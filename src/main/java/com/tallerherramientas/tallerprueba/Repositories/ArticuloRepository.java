package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticuloRepository extends JpaRepository<Articulo,Long> {
    List<Articulo> findByTitulo(String titulo);

//    @Query("SELECT r FROM Articulo r WHERE LOWER(r.titulo) LIKE %:titulo%")
    //List<Articulo> findByAproximado(String titulo);

    List<Articulo> findByTituloContainingIgnoreCase(String titulo);


    Optional<Articulo> findByCodigoDeProducto(String codigoDeProducto); // Método para buscar por código

}
