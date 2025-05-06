package com.tallerherramientas.tallerprueba.Servicios.Contratos;

import com.tallerherramientas.tallerprueba.Modelo.Entidades.Stock;

import java.util.Optional;

public interface StockDAO {
   // List<Stock> obtenerTodos();
    Optional<Stock> obtenerPorRepuestoId(Long repuestoId);
    Stock guardarStock(Stock stock);
    //void eliminarStock(Long id);//revisar
}
