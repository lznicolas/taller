package com.tallerherramientas.tallerprueba.Services.Implementaciones.RepuestoStock;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Stock;
import com.tallerherramientas.tallerprueba.Repositories.StockRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.StockDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockDAOImp implements StockDAO {
    @Autowired
    private final StockRepository stockRepository;

    @Override
    public Stock guardar(Stock entidad) {
        return stockRepository.save(entidad);
    }

    @Override
    public List<Stock> listar() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> obtenerPorId(Long aLong) {
        return stockRepository.findByRepuestoId(aLong);
    }

    @Override
    public void eliminar(Long aLong) {
        stockRepository.deleteById(aLong);
    }
}
