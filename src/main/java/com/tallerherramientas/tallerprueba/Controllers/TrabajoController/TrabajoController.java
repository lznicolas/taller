package com.tallerherramientas.tallerprueba.Controllers.TrabajoController;

import com.tallerherramientas.tallerprueba.Modelo.DTO.TrabajoDTO;
import com.tallerherramientas.tallerprueba.Modelo.DTO.TrabajoDetalleDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.*;
import com.tallerherramientas.tallerprueba.Services.Contratos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
}
