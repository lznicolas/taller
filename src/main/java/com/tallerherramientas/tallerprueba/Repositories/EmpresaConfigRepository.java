package com.tallerherramientas.tallerprueba.Repositories;

import com.tallerherramientas.tallerprueba.Modelo.Entities.EmpresaConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaConfigRepository extends JpaRepository<EmpresaConfig, Long> {
    Optional<EmpresaConfig> findFirstByOrderByIdAsc();
}
