package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
