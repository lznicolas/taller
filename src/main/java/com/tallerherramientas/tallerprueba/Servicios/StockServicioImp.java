package com.tallerherramientas.tallerprueba.Servicios;

import com.tallerherramientas.tallerprueba.Modelo.Stock;
import com.tallerherramientas.tallerprueba.Repositorio.StockRepository;
import com.tallerherramientas.tallerprueba.Servicios.Interfaz.StockServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServicioImp implements StockServicio {
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
