package com.tallerherramientas.tallerprueba.Servicios.Implementaciones.RepuestoStock;

import com.tallerherramientas.tallerprueba.Modelo.Entidades.Stock;
import com.tallerherramientas.tallerprueba.Repositorio.StockRepository;
import com.tallerherramientas.tallerprueba.Servicios.Contratos.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockDAOImp implements StockDAO {
    @Autowired
    private StockRepository stockRepository;


    /*@Override
    public List<Stock> obtenerTodos() {
        return stockRepository.findAll();
    }*/

    @Override
    public Optional<Stock> obtenerPorRepuestoId(Long repuestoId) {
        return stockRepository.findByRepuestoId(repuestoId);
    }

    @Override
    public Stock guardarStock(Stock stock) {
        return stockRepository.save(stock);
    }

    /*@Override
    public void eliminarStock(Long id) {
        stockRepository.deleteById(id);
    }*/
}
