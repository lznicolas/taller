package com.tallerherramientas.tallerprueba.Controllers.TrabajoController;

import com.tallerherramientas.tallerprueba.Modelo.DTO.TrabajoDTO;
import com.tallerherramientas.tallerprueba.Modelo.DTO.TrabajoDetalleDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.*;
import com.tallerherramientas.tallerprueba.Services.Contratos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trabajos")
@CrossOrigin(origins = "http://localhost:5173")
public class TrabajoController {
    @Autowired
    private TrabajoDAO trabajoDAO;

    @PostMapping
    public ResponseEntity<?> crearTrabajo(@RequestBody TrabajoDTO trabajoDTO){
        try{
            Trabajo trabajo = trabajoDAO.guardarDesdeDTO(trabajoDTO);
            return new ResponseEntity<>(trabajo, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error al crear Trabajo",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajoDetalleDTO> obtenerPorId(@PathVariable Long id) {
        try {
            TrabajoDetalleDTO dto = trabajoDAO.obtenerTrabajoDetallePorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Buscar a traves de un cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<TrabajoDTO>> obtenerTrabajoPorClienteId(@PathVariable Long clienteId){
       List<TrabajoDTO>trabajos = trabajoDAO.BuscarTrabajosPorCliente(clienteId);
       if(trabajos.isEmpty()){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.ok(trabajos);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TrabajoDetalleDTO>> listarTrabajos(){
        List<TrabajoDetalleDTO> trabajos = trabajoDAO.listarDetalles();
        if(trabajos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trabajos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTrabajo(@PathVariable Long id, @RequestBody TrabajoDTO trabajoDTO){
        try{
            Trabajo actualizado = trabajoDAO.actualizarDesdeDTO(id, trabajoDTO);
            return ResponseEntity.ok(actualizado);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajo no encontrado");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar trabajo");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTrabajo(@PathVariable Long id){
        Optional<Trabajo> trabajo = trabajoDAO.obtenerPorId(id);
        if(trabajo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        trabajoDAO.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
