package com.tallerherramientas.tallerprueba.Services.Contratos;
import java.util.List;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Articulo;

public interface ArticuloDAO extends ServicioGenericoDAO<Articulo,Long>{
    Articulo buscarArticuloPorTitulo(String titulo);
    Articulo buscarPorAproximado(String titulo);
    Articulo buscarPorCodigoDeProducto(String codigoDeProducto);
}
