package com.tallerherramientas.tallerprueba.Controlador;

import com.tallerherramientas.tallerprueba.Modelo.Repuesto;
import com.tallerherramientas.tallerprueba.Modelo.Stock;
import com.tallerherramientas.tallerprueba.Servicios.Interfaz.RepuestoServicio;
import com.tallerherramientas.tallerprueba.Servicios.Interfaz.StockServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repuestos")
public class Controlador {

    //Declaracion de  tipo de Servicio
    @Autowired
    private RepuestoServicio repuestoServicio;
    @Autowired
    private StockServicio  stockServicio;

    //Repuesto
    @GetMapping("/all")
    public List<Repuesto> obtenerTodosLosRespuestos(){
        return repuestoServicio.obtenerTodosLosRepuestos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repuesto> obtenerRepuestoPorId(@PathVariable Long id){
        Repuesto repuesto = repuestoServicio.obtenerRepuestoPorId(id);
        if(repuesto != null){
            return new ResponseEntity<>(repuesto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Repuesto crearRepuesto(@RequestBody Repuesto repuesto){
        return repuestoServicio.guardarRepuesto(repuesto);
    }

    @DeleteMapping("/{id}")
    public void eliminarRepuesto(@PathVariable Long id){
        repuestoServicio.eliminarRepuesto(id);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Repuesto> obtenerRepuestoPorTitulo(@PathVariable String titulo){
        Repuesto repuesto = repuestoServicio.buscarRepuestoPorTitulo(titulo);
        if(repuesto != null){
            return new ResponseEntity<>(repuesto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/aprox/{titulo}")
    public ResponseEntity<Repuesto> obtenerRepuestoAproximado(@PathVariable String titulo){
        Repuesto repuesto = repuestoServicio.buscarPorAproximado(titulo);
        if(repuesto != null){
            return new ResponseEntity<>(repuesto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Repuesto> obtenerRepuestoPorCodigo(@PathVariable String codigo) {
        Repuesto repuesto = repuestoServicio.buscarPorCodigoDeProducto(codigo);
        return repuesto != null
                ? new ResponseEntity<>(repuesto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Stock
    /*@GetMapping("/stock/all")
    public List<Stock> obtenerTodos(){
        return stockServicio.obtenerTodos();
    }*/
    @GetMapping("/codigo/{codigo}/stock")
    public ResponseEntity<Stock>obtenerStockPorCodigo(@PathVariable String codigo){
        Repuesto repuesto= repuestoServicio.buscarPorCodigoDeProducto(codigo);
        if (repuesto != null){
            return stockServicio.obtenerPorRepuestoId(repuesto.getId())
                    .map(stock -> new ResponseEntity<>(stock,HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/codigo/{codigo}/stock")
    public ResponseEntity<Stock> agregaStock(@PathVariable String codigo, @RequestBody Integer cantidad){
        Repuesto repuesto = repuestoServicio.buscarPorCodigoDeProducto(codigo);
        if(repuesto !=null){
            Optional<Stock>stockExistente = stockServicio.obtenerPorRepuestoId(repuesto.getId());

            Stock stock = stockExistente.orElse(new Stock(repuesto,0));
            stock.setCantidad(stock.getCantidad() + cantidad);
            return new ResponseEntity<>(stockServicio.guardarStock(stock),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
