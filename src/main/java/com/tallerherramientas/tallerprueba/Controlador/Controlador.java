package com.tallerherramientas.tallerprueba.Controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@CrossOrigin(origins = "http://localhost:5173")
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
    /*public Repuesto crearRepuesto(@RequestBody Repuesto repuesto){
        System.out.println("Repuesto recibido: "+repuesto);
        System.out.println("Repuesto codigoDeProducto: "+repuesto.getCodigoDeProducto());
        System.out.println("Repuesto Titulo: "+repuesto.getTitulo());
        System.out.println("Repuesto Descripcion: "+repuesto.getDescripcion());
        System.out.println("Repuesto Ubicacion: "+repuesto.getUbicacion());
        return repuestoServicio.guardarRepuesto(repuesto);
    }
     */
    public ResponseEntity<Repuesto>crearRepuesto(@RequestBody String jsonRepuesto){
        ObjectMapper mapper = new ObjectMapper();
        try{
            Repuesto repuesto = mapper.readValue(jsonRepuesto, Repuesto.class);
            System.out.println("Objeto deserializado: "+repuesto.getTitulo());
            return new ResponseEntity<>(repuestoServicio.guardarRepuesto(repuesto),HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repuesto> actualizarRepuesto(@PathVariable Long id, @RequestBody Repuesto repuesto){
        Repuesto repuestoExistente = repuestoServicio.obtenerRepuestoPorId(id);
        if (repuestoExistente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repuestoExistente.setTitulo(repuesto.getTitulo());
        repuestoExistente.setDescripcion(repuesto.getDescripcion());
        repuestoExistente.setUbicacion(repuesto.getUbicacion());
        return new ResponseEntity<>(repuestoServicio.guardarRepuesto(repuestoExistente),HttpStatus.OK);
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
    public ResponseEntity<Stock> agregaStock(@PathVariable String codigo, @RequestBody Stock stockRequest){
        Repuesto repuesto = repuestoServicio.buscarPorCodigoDeProducto(codigo);
        if(repuesto !=null){
            Optional<Stock>stockExistente = stockServicio.obtenerPorRepuestoId(repuesto.getId());

            Stock stock = stockExistente.orElse(new Stock(repuesto,0));
            stock.setCantidad(stock.getCantidad() + stockRequest.getCantidad());
            return new ResponseEntity<>(stockServicio.guardarStock(stock),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/codigo/{codigo}/stock")
    public ResponseEntity<Stock> actulizarStock(@PathVariable String codigo, @RequestBody Stock stockRequest){
        Repuesto repuesto = repuestoServicio.buscarPorCodigoDeProducto(codigo);

        if(repuesto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Stock> stockExistence = stockServicio.obtenerPorRepuestoId(repuesto.getId());

        Stock stock = stockExistence.orElse(new Stock(repuesto,0));
        stock.setCantidad(stock.getCantidad() + stockRequest.getCantidad());
        stockServicio.guardarStock(stock);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
}
