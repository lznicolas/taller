package com.tallerherramientas.tallerprueba.Services.Implementaciones.Detalles;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleRepuestoDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleRepuestoTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Repuesto;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Stock;
import com.tallerherramientas.tallerprueba.Repositories.DetalleRepuestoTrabajoRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleRepuestoTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.RepuestoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.StockDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleRepuestoTrabajoDAOImp implements DetalleRepuestoTrabajoDAO {
    @Autowired
    private final DetalleRepuestoTrabajoRepository detalleRepuestoTrabajoRepository;
    @Autowired
    private RepuestoDAO repuestoDAO;
    @Autowired
    private StockDAO stockDAO;
    @Override
    public DetalleRepuestoTrabajo guardar(DetalleRepuestoTrabajo entidad) {
        return detalleRepuestoTrabajoRepository.save(entidad);
    }

    @Override
    public List<DetalleRepuestoTrabajo> listar() {
        return detalleRepuestoTrabajoRepository.findAll();
    }

    @Override
    public Optional<DetalleRepuestoTrabajo> obtenerPorId(Long aLong) {
        Optional<DetalleRepuestoTrabajo> detalleRepuestoTrabajo = detalleRepuestoTrabajoRepository.findById(aLong);
        return Optional.ofNullable(detalleRepuestoTrabajo.orElse(null));
    }

    @Override
    public void eliminar(Long aLong) {
        detalleRepuestoTrabajoRepository.deleteById(aLong);
    }

    @Override
    public void guardarDetalleRepuestoTrabajo(List<DetalleRepuestoDTO> repuestoDTOS, Trabajo trabajo) {
        for(DetalleRepuestoDTO repuestoDTO : repuestoDTOS){
            Repuesto repuesto = repuestoDAO.obtenerPorId(repuestoDTO.getRepuestoId())
                    .orElseThrow(()->new RuntimeException("Repuesto no encontrado con el ID: "+repuestoDTO.getRepuestoId()));

            Integer cantidadSolicitada = repuestoDTO.getCantidadUsada() != null ? repuestoDTO.getCantidadUsada() : 0;
            if (cantidadSolicitada < 0) {
                throw new RuntimeException("La cantidad usada no puede ser negativa para el repuesto " + repuesto.getTitulo());
            }
            Stock stock = stockDAO.obtenerPorId(repuesto.getId()).orElse(new Stock(repuesto, 0));
            int disponible = stock.getCantidad() != null ? stock.getCantidad() : 0;
            if (cantidadSolicitada > disponible) {
                throw new RuntimeException("Stock insuficiente para '" + repuesto.getTitulo() + "'. Disponible: " + disponible);
            }
            stock.setCantidad(disponible - cantidadSolicitada);
            stockDAO.guardar(stock);

            DetalleRepuestoTrabajo detalleRepuestoTrabajo= new DetalleRepuestoTrabajo();

            detalleRepuestoTrabajo.setRepuesto(repuesto);
            detalleRepuestoTrabajo.setTrabajo(trabajo);
            detalleRepuestoTrabajo.setCantidadUsada(cantidadSolicitada);
            detalleRepuestoTrabajoRepository.save(detalleRepuestoTrabajo);
        }
    }
}
