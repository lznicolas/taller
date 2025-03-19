package com.tallerherramientas.tallerprueba.Servicios.Interfaz;
import java.util.List;
import com.tallerherramientas.tallerprueba.Modelo.Repuesto;

public interface RepuestoServicio {
    List<Repuesto> obtenerTodosLosRepuestos();
    Repuesto obtenerRepuestoPorId(Long id);
    Repuesto guardarRepuesto(Repuesto repuesto);
    void eliminarRepuesto(Long id);
    Repuesto buscarRepuestoPorTitulo(String titulo);
    Repuesto buscarPorAproximado(String titulo);

    Repuesto buscarPorCodigoDeProducto(String codigoDeProducto);
}
