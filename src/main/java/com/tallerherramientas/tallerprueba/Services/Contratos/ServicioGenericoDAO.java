package com.tallerherramientas.tallerprueba.Services.Contratos;

import java.util.List;
import java.util.Optional;

public interface ServicioGenericoDAO <T,ID>{
    T guardar(T entidad);
    List<T> listar();
    Optional<T> obtenerPorId(ID id);
    void eliminar(ID id);
}
