package com.tallerherramientas.tallerprueba.Repositorio;

import com.tallerherramientas.tallerprueba.Modelo.Entidades.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajoRepository extends JpaRepository<Trabajo,Long> {
}
