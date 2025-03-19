package com.tallerherramientas.tallerprueba.Repositorio;

import com.tallerherramientas.tallerprueba.Modelo.Stock;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findByRepuestoId(Long repuestoId);
}
