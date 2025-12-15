package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import com.tallerherramientas.tallerprueba.Modelo.Enums.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
    @Query("select max(cast(e.legajo as integer)) from Empleado e where e.especialidad = :especialidad")
    Integer findMaxLegajoByEspecialidad(@Param("especialidad") Especialidad especialidad);
}
