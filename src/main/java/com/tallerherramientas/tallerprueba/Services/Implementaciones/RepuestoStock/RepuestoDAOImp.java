package com.tallerherramientas.tallerprueba.Services.Implementaciones.RepuestoStock;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Repuesto;
import com.tallerherramientas.tallerprueba.Repositories.RepuestoRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.RepuestoDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepuestoDAOImp implements RepuestoDAO {

    @Autowired
    private final RepuestoRepository repuestoRepository;
    @Override
    public Repuesto buscarRepuestoPorTitulo(String titulo) {
        List<Repuesto> repuestos = repuestoRepository.findByTitulo(titulo);
        if (!repuestos.isEmpty()) {
            return repuestos.get(0); // Devuelve el primer resultado
        } else {
            return null; // No se encontró ningún repuesto
        }

    }

    @Override
    public Repuesto buscarPorAproximado(String titulo) {
        List<Repuesto> repuestos = repuestoRepository.findByTituloContainingIgnoreCase(titulo);
        if (!repuestos.isEmpty()) {
            return repuestos.get(0); // Devuelve el primer resultado
        } else {
            return null; // No se encontró ningún repuesto
        }
    }
    @Override
    public Repuesto buscarPorCodigoDeProducto(String codigoDeProducto) {
        Optional<Repuesto> repuesto = repuestoRepository.findByCodigoDeProducto(codigoDeProducto);
        return repuesto.orElse(null);
    }

    @Override
    public Repuesto guardar(Repuesto entidad) {
        return repuestoRepository.save(entidad);
    }

    @Override
    public List<Repuesto> listar() {
        return repuestoRepository.findAll();
    }

    @Override
    public Optional<Repuesto> obtenerPorId(Long aLong) {
        return repuestoRepository.findById(aLong);
    }

    @Override
    public void eliminar(Long aLong) {
        repuestoRepository.deleteById(aLong);

    }
}
