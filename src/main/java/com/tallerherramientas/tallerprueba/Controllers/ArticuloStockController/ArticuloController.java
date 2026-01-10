package com.tallerherramientas.tallerprueba.Controllers.ArticuloStockController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Articulo;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Stock;
import com.tallerherramientas.tallerprueba.Services.Contratos.ArticuloDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    //Declaracion de  tipo de Servicio
    @Autowired
    private ArticuloDAO articuloDAO;
    @Autowired
    private StockDAO stockDAO;

    //Articulo
    @GetMapping("/all")
    public List<Articulo> obtenerTodosLosRespuestos(){
        return articuloDAO.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> obtenerArticuloPorId(@PathVariable Long id){
        Articulo articulo = articuloDAO.obtenerPorId(id).orElse(null);
        if (articulo != null){
            return new ResponseEntity<>(articulo,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Articulo> guardarArticulo(@RequestBody String jsonArticulo){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Articulo articulo = mapper.readValue(jsonArticulo, Articulo.class);
            System.out.println("Objeto deserializado: "+articulo.getTitulo());
            return new ResponseEntity<>(articuloDAO.guardar (articulo),HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Articulo> actualizarArticulo(@PathVariable Long id, @RequestBody Articulo articulo){
        Articulo articuloExistente = articuloDAO.obtenerPorId(id).orElse(null);
        if (articuloExistente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        articuloExistente.setCodigoDeProducto(articulo.getCodigoDeProducto());
        articuloExistente.setTitulo(articulo.getTitulo());
        articuloExistente.setDescripcion(articulo.getDescripcion());
        articuloExistente.setUbicacion(articulo.getUbicacion());
        articuloExistente.setPrecioUnitario(articulo.getPrecioUnitario());
        articuloExistente.setCategoria(articulo.getCategoria());
        return new ResponseEntity<>(articuloDAO.guardar(articuloExistente),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Articulo articulo = articuloDAO.obtenerPorId(id).orElse(null);
        if(articulo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        articuloDAO.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Articulo> obtenerArticuloPorTitulo(@PathVariable String titulo){
        Articulo articulo = articuloDAO.buscarArticuloPorTitulo(titulo);
        if(articulo != null){
            return new ResponseEntity<>(articulo, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/aprox/{titulo}")
    public ResponseEntity<Articulo> obtenerArticuloAproximado(@PathVariable String titulo){
        Articulo articulo = articuloDAO.buscarPorAproximado(titulo);
        if(articulo != null){
            return new ResponseEntity<>(articulo, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Articulo> obtenerArticuloPorCodigo(@PathVariable String codigo) {
        Articulo articulo = articuloDAO.buscarPorCodigoDeProducto(codigo);
        return articulo != null
                ? new ResponseEntity<>(articulo, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Stock
    /*@GetMapping("/stock/all")
    public List<Stock> obtenerTodos(){
        return stockDAO.obtenerTodos();
    }*/
    @GetMapping("/codigo/{codigo}/stock")
    public ResponseEntity<Stock>obtenerStockPorCodigo(@PathVariable String codigo){
        Articulo articulo = articuloDAO.buscarPorCodigoDeProducto(codigo);
        if (articulo != null){
            return stockDAO.obtenerPorId(articulo.getId())
                    .map(stock -> new ResponseEntity<>(stock,HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/codigo/{codigo}/stock")
    public ResponseEntity<Stock> agregaStock(@PathVariable String codigo, @RequestBody Stock stockRequest){
        Articulo articulo = articuloDAO.buscarPorCodigoDeProducto(codigo);
        if(articulo !=null){
            Optional<Stock>stockExistente = stockDAO.obtenerPorId(articulo.getId());

            Stock stock = stockExistente.orElse(new Stock(articulo,0));
            stock.setCantidad(stock.getCantidad() + stockRequest.getCantidad());
            return new ResponseEntity<>(stockDAO.guardar(stock),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/codigo/{codigo}/stock")
    public ResponseEntity<Stock> actulizarStock(@PathVariable String codigo, @RequestBody Stock stockRequest){
        Articulo articulo = articuloDAO.buscarPorCodigoDeProducto(codigo);

        if(articulo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Stock> stockExistence = stockDAO.obtenerPorId(articulo.getId());

        Stock stock = stockExistence.orElse(new Stock(articulo,0));
        // el cliente envía la cantidad final deseada, no el incremento
        stock.setCantidad(stockRequest.getCantidad());
        stockDAO.guardar(stock);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
}
