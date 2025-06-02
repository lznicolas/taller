package com.tallerherramientas.tallerprueba.Services.Contratos;
import java.util.List;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Repuesto;

public interface RepuestoDAO extends ServicioGenericoDAO<Repuesto,Long>{
    Repuesto buscarRepuestoPorTitulo(String titulo);
    Repuesto buscarPorAproximado(String titulo);
    Repuesto buscarPorCodigoDeProducto(String codigoDeProducto);
}
