package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
