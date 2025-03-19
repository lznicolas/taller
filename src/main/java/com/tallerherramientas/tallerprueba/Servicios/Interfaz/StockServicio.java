package com.tallerherramientas.tallerprueba.Servicios.Interfaz;

import com.tallerherramientas.tallerprueba.Modelo.Stock;

import java.util.Optional;

public interface StockServicio {
   // List<Stock> obtenerTodos();
    Optional<Stock> obtenerPorRepuestoId(Long repuestoId);
    Stock guardarStock(Stock stock);
    //void eliminarStock(Long id);//revisar
}
