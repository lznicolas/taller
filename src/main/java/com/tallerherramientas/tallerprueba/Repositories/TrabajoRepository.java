package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.DTO.TrabajoDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TrabajoRepository extends JpaRepository<Trabajo,Long> {
    //Trabajo guardarDesdeDTO(TrabajoDTO dto);



}
