package com.tallerherramientas.tallerprueba.Controllers.PersonaController;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import com.tallerherramientas.tallerprueba.Modelo.Enums.Especialidad;
import com.tallerherramientas.tallerprueba.Services.Contratos.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpleadoController {
    @Autowired
    private EmpleadoDAO empleadoDAO;

    @GetMapping("/all")
    public List<Empleado> listar(){
        return empleadoDAO.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado>obtenerPorId(@PathVariable Long id){
        Empleado empleado = empleadoDAO.obtenerPorId(id).orElse(null);
        if(empleado != null){
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado empleado){
        if (empleado.getEspecialidad() == null) {
            return ResponseEntity.badRequest().build();
        }
        empleadoDAO.ajustarLegajoSegunRegla(empleado);
        Empleado nuevoEmpleado = empleadoDAO.guardar(empleado);
        return new ResponseEntity<>(nuevoEmpleado,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Empleado>actualizar(@PathVariable Long id, @RequestBody Empleado empleado){
        Empleado existente = empleadoDAO.obtenerPorId(id).orElse(null);
        if (existente == null){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        if (empleado.getEspecialidad() == null) {
            return ResponseEntity.badRequest().build();
        }
        existente.setNombre(empleado.getNombre());
        existente.setApellido(empleado.getApellido());
        existente.setTelefono(empleado.getTelefono());
        existente.setDireccion(empleado.getDireccion());
        existente.setEspecialidad(empleado.getEspecialidad());
        existente.setLegajo(empleadoDAO.ajustarLegajoSegunRegla(empleado));
        existente.setSueldo(empleado.getSueldo());

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

    @GetMapping("/especialidades")
    public List<Especialidad> listarEspecialidades() {
        return empleadoDAO.listarEspecialidades();
    }

    @GetMapping("/legajos/sugerido")
    public ResponseEntity<Integer> legajoSugerido(@RequestParam("especialidad") String especialidadStr) {
        try {
            Especialidad especialidad = Especialidad.valueOf(especialidadStr.toUpperCase());
            Integer sugerido = empleadoDAO.obtenerSiguienteLegajo(especialidad);
            return ResponseEntity.ok(sugerido);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
