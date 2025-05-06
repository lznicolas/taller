package com.tallerherramientas.tallerprueba.Repositorio;

import com.tallerherramientas.tallerprueba.Modelo.Entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
