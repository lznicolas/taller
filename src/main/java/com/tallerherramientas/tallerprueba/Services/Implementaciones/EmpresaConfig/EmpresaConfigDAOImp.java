package com.tallerherramientas.tallerprueba.Services.Implementaciones.EmpresaConfig;

import com.tallerherramientas.tallerprueba.Modelo.Entities.EmpresaConfig;
import com.tallerherramientas.tallerprueba.Repositories.EmpresaConfigRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.EmpresaConfigDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaConfigDAOImp implements EmpresaConfigDAO {
    @Autowired
    private final EmpresaConfigRepository empresaConfigRepository;

    @Override
    public EmpresaConfig guardar(EmpresaConfig entidad) {
        return empresaConfigRepository.save(entidad);
    }

    @Override
    public List<EmpresaConfig> listar() {
        return empresaConfigRepository.findAll();
    }

    @Override
    public Optional<EmpresaConfig> obtenerPorId(Long aLong) {
        Optional<EmpresaConfig> empresaConfig = empresaConfigRepository.findById(aLong);
        return Optional.ofNullable(empresaConfig.orElse(null));
    }

    @Override
    public void eliminar(Long aLong) {
        empresaConfigRepository.deleteById(aLong);
    }

    @Override
    public Optional<EmpresaConfig> obtenerPrimera() {
        return empresaConfigRepository.findFirstByOrderByIdAsc();
    }
}
