package com.tallerherramientas.tallerprueba.Services.Implementaciones.RepuestoStock;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Repuesto;
import com.tallerherramientas.tallerprueba.Repositories.RepuestoRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.RepuestoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoDAOImp implements RepuestoDAO {

    @Autowired
    private RepuestoRepository repuestoRepository;
    @Override
    public List<Repuesto> obtenerTodosLosRepuestos() {
        return repuestoRepository.findAll();
    }

    @Override
    public Repuesto obtenerRepuestoPorId(Long id) {
        Optional<Repuesto> repuesto = repuestoRepository.findById(id);
        return repuesto.orElse(null);
    }

    @Override
    public Repuesto guardarRepuesto(Repuesto repuesto) {

        return repuestoRepository.save(repuesto);
    }

    @Override
    public void eliminarRepuesto(Long id) {
        repuestoRepository.deleteById(id);
    }

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
        List<Repuesto> repuestos = repuestoRepository.findByAproximado(titulo);
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
}
