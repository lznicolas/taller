package com.tallerherramientas.tallerprueba.Services.Contratos;

import com.tallerherramientas.tallerprueba.Modelo.Entities.EmpresaConfig;

import java.util.Optional;

public interface EmpresaConfigDAO extends ServicioGenericoDAO<EmpresaConfig, Long> {
    Optional<EmpresaConfig> obtenerPrimera();
}
