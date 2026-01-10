package com.tallerherramientas.tallerprueba.Services.Implementaciones.Detalles;

import com.tallerherramientas.tallerprueba.Modelo.DTO.DetalleArticuloDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.DetalleArticuloTrabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Articulo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Trabajo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Stock;
import com.tallerherramientas.tallerprueba.Repositories.DetalleArticuloTrabajoRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.DetalleArticuloTrabajoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.ArticuloDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.StockDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleArticuloTrabajoDAOImp implements DetalleArticuloTrabajoDAO {
    @Autowired
    private final DetalleArticuloTrabajoRepository detalleArticuloTrabajoRepository;
    @Autowired
    private ArticuloDAO articuloDAO;
    @Autowired
    private StockDAO stockDAO;
    @Override
    public DetalleArticuloTrabajo guardar(DetalleArticuloTrabajo entidad) {
        return detalleArticuloTrabajoRepository.save(entidad);
    }

    @Override
    public List<DetalleArticuloTrabajo> listar() {
        return detalleArticuloTrabajoRepository.findAll();
    }

    @Override
    public Optional<DetalleArticuloTrabajo> obtenerPorId(Long aLong) {
        Optional<DetalleArticuloTrabajo> detalleArticuloTrabajo = detalleArticuloTrabajoRepository.findById(aLong);
        return Optional.ofNullable(detalleArticuloTrabajo.orElse(null));
    }

    @Override
    public void eliminar(Long aLong) {
        detalleArticuloTrabajoRepository.deleteById(aLong);
    }

    @Override
    public void guardarDetalleArticuloTrabajo(List<DetalleArticuloDTO> articuloDTOS, Trabajo trabajo) {
        for(DetalleArticuloDTO articuloDTO : articuloDTOS){
            Articulo articulo = articuloDAO.obtenerPorId(articuloDTO.getArticuloId())
                    .orElseThrow(()->new RuntimeException("Articulo no encontrado con el ID: "+articuloDTO.getArticuloId()));

            Integer cantidadSolicitada = articuloDTO.getCantidadUsada() != null ? articuloDTO.getCantidadUsada() : 0;
            if (cantidadSolicitada < 0) {
                throw new RuntimeException("La cantidad usada no puede ser negativa para el articulo " + articulo.getTitulo());
            }
            Stock stock = stockDAO.obtenerPorId(articulo.getId()).orElse(new Stock(articulo, 0));
            int disponible = stock.getCantidad() != null ? stock.getCantidad() : 0;
            if (cantidadSolicitada > disponible) {
                throw new RuntimeException("Stock insuficiente para '" + articulo.getTitulo() + "'. Disponible: " + disponible);
            }
            stock.setCantidad(disponible - cantidadSolicitada);
            stockDAO.guardar(stock);

            DetalleArticuloTrabajo detalleArticuloTrabajo= new DetalleArticuloTrabajo();

            detalleArticuloTrabajo.setArticulo(articulo);
            detalleArticuloTrabajo.setTrabajo(trabajo);
            detalleArticuloTrabajo.setCantidadUsada(cantidadSolicitada);
            detalleArticuloTrabajoRepository.save(detalleArticuloTrabajo);
        }
    }
}
