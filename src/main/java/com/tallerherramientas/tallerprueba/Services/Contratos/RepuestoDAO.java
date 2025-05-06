package com.tallerherramientas.tallerprueba.Services.Contratos;
import java.util.List;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Repuesto;

public interface RepuestoDAO {
    List<Repuesto> obtenerTodosLosRepuestos();
    Repuesto obtenerRepuestoPorId(Long id);
    Repuesto guardarRepuesto(Repuesto repuesto);
    void eliminarRepuesto(Long id);
    Repuesto buscarRepuestoPorTitulo(String titulo);
    Repuesto buscarPorAproximado(String titulo);

    Repuesto buscarPorCodigoDeProducto(String codigoDeProducto);
}
