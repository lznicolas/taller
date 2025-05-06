package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabajoRepository extends JpaRepository<Trabajo,Long> {
}
