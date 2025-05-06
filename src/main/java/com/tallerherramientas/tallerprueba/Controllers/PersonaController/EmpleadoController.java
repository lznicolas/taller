package com.tallerherramientas.tallerprueba.Controllers.PersonaController;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Cliente;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import com.tallerherramientas.tallerprueba.Services.Contratos.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpleadoController {
    @Autowired
    private EmpleadoDAO empleadoDAO;

    @GetMapping("/all")
    public List<Empleado> listar(){
        return empleadoDAO.listar();
    }

    @GetMapping("{id}")
    public ResponseEntity<Empleado>obtenerPorId(@PathVariable Long id){
        Empleado empleado = empleadoDAO.obtenerPorId(id).orElse(null);
        if(empleado != null){
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Empleado> crear(@RequestBody Empleado empleado){
        Empleado nuevoEmpleado = empleadoDAO.guardar(empleado);
        return new ResponseEntity<>(nuevoEmpleado,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Empleado>actualizar(@PathVariable Long id, @RequestBody Empleado empleado){
        Empleado existente = empleadoDAO.obtenerPorId(id).orElse(null);
        if (existente == null){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        existente.setNombre(empleado.getNombre());
        existente.setApellido(empleado.getApellido());
        existente.setTelefono(empleado.getTelefono());
        existente.setDireccion(empleado.getDireccion());

        return ResponseEntity.ok(empleadoDAO.guardar(existente));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Empleado empleado = empleadoDAO.obtenerPorId(id).orElse(null);
        if(empleado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        empleadoDAO.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
