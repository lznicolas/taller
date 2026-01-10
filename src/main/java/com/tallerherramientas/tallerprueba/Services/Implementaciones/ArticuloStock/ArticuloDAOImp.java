package com.tallerherramientas.tallerprueba.Services.Implementaciones.ArticuloStock;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Articulo;
import com.tallerherramientas.tallerprueba.Repositories.ArticuloRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.ArticuloDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticuloDAOImp implements ArticuloDAO {

    @Autowired
    private final ArticuloRepository articuloRepository;
    @Override
    public Articulo buscarArticuloPorTitulo(String titulo) {
        List<Articulo> articulos = articuloRepository.findByTitulo(titulo);
        if (!articulos.isEmpty()) {
            return articulos.get(0); // Devuelve el primer resultado
        } else {
            return null; // No se encontró ningún articulo
        }

    }

    @Override
    public Articulo buscarPorAproximado(String titulo) {
        List<Articulo> articulos = articuloRepository.findByTituloContainingIgnoreCase(titulo);
        if (!articulos.isEmpty()) {
            return articulos.get(0); // Devuelve el primer resultado
        } else {
            return null; // No se encontró ningún articulo
        }
    }
    @Override
    public Articulo buscarPorCodigoDeProducto(String codigoDeProducto) {
        Optional<Articulo> articulo = articuloRepository.findByCodigoDeProducto(codigoDeProducto);
        return articulo.orElse(null);
    }

    @Override
    public Articulo guardar(Articulo entidad) {
        return articuloRepository.save(entidad);
    }

    @Override
    public List<Articulo> listar() {
        return articuloRepository.findAll();
    }

    @Override
    public Optional<Articulo> obtenerPorId(Long aLong) {
        return articuloRepository.findById(aLong);
    }

    @Override
    public void eliminar(Long aLong) {
        articuloRepository.deleteById(aLong);

    }
}
