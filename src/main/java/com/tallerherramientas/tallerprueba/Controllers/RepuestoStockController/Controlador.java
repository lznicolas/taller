package com.tallerherramientas.tallerprueba.Controllers.RepuestoStockController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Repuesto;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Stock;
import com.tallerherramientas.tallerprueba.Services.Contratos.RepuestoDAO;
import com.tallerherramientas.tallerprueba.Services.Contratos.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/repuestos")
public class Controlador {

    //Declaracion de  tipo de Servicio
    @Autowired
    private RepuestoDAO repuestoDAO;
    @Autowired
    private StockDAO stockDAO;

    //Repuesto
    @GetMapping("/all")
    public List<Repuesto> obtenerTodosLosRespuestos(){
        return repuestoDAO.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> obtenerRepuestoPorId(@PathVariable Long id){
        Repuesto repuesto = repuestoDAO.obtenerPorId(id).orElse(null);
        if (repuesto != null){
            return new ResponseEntity<>(repuesto,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Repuesto>guardarRepuesto (@RequestBody String jsonRepuesto){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Repuesto repuesto = mapper.readValue(jsonRepuesto, Repuesto.class);
            System.out.println("Objeto deserializado: "+repuesto.getTitulo());
            return new ResponseEntity<>(repuestoDAO.guardar (repuesto),HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> actualizarRepuesto(@PathVariable Long id, @RequestBody Repuesto repuesto){
        Repuesto repuestoExistente = repuestoDAO.obtenerPorId(id).orElse(null);
        if (repuestoExistente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repuestoExistente.setTitulo(repuesto.getTitulo());
        repuestoExistente.setDescripcion(repuesto.getDescripcion());
        repuestoExistente.setUbicacion(repuesto.getUbicacion());
        return new ResponseEntity<>(repuestoDAO.guardar(repuestoExistente),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Repuesto repuesto = repuestoDAO.obtenerPorId(id).orElse(null);
        if(repuesto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repuestoDAO.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Repuesto> obtenerRepuestoPorTitulo(@PathVariable String titulo){
        Repuesto repuesto = repuestoDAO.buscarRepuestoPorTitulo(titulo);
        if(repuesto != null){
            return new ResponseEntity<>(repuesto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/aprox/{titulo}")
    public ResponseEntity<Repuesto> obtenerRepuestoAproximado(@PathVariable String titulo){
        Repuesto repuesto = repuestoDAO.buscarPorAproximado(titulo);
        if(repuesto != null){
            return new ResponseEntity<>(repuesto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Repuesto> obtenerRepuestoPorCodigo(@PathVariable String codigo) {
        Repuesto repuesto = repuestoDAO.buscarPorCodigoDeProducto(codigo);
        return repuesto != null
                ? new ResponseEntity<>(repuesto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Stock
    /*@GetMapping("/stock/all")
    public List<Stock> obtenerTodos(){
        return stockDAO.obtenerTodos();
    }*/
    @GetMapping("/codigo/{codigo}/stock")
    public ResponseEntity<Stock>obtenerStockPorCodigo(@PathVariable String codigo){
        Repuesto repuesto= repuestoDAO.buscarPorCodigoDeProducto(codigo);
        if (repuesto != null){
            return stockDAO.obtenerPorId(repuesto.getId())
                    .map(stock -> new ResponseEntity<>(stock,HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/codigo/{codigo}/stock")
    public ResponseEntity<Stock> agregaStock(@PathVariable String codigo, @RequestBody Stock stockRequest){
        Repuesto repuesto = repuestoDAO.buscarPorCodigoDeProducto(codigo);
        if(repuesto !=null){
            Optional<Stock>stockExistente = stockDAO.obtenerPorId(repuesto.getId());

            Stock stock = stockExistente.orElse(new Stock(repuesto,0));
            stock.setCantidad(stock.getCantidad() + stockRequest.getCantidad());
            return new ResponseEntity<>(stockDAO.guardar(stock),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/codigo/{codigo}/stock")
    public ResponseEntity<Stock> actulizarStock(@PathVariable String codigo, @RequestBody Stock stockRequest){
        Repuesto repuesto = repuestoDAO.buscarPorCodigoDeProducto(codigo);

        if(repuesto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Stock> stockExistence = stockDAO.obtenerPorId(repuesto.getId());

        Stock stock = stockExistence.orElse(new Stock(repuesto,0));
        stock.setCantidad(stock.getCantidad() + stockRequest.getCantidad());
        stockDAO.guardar(stock);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
}
