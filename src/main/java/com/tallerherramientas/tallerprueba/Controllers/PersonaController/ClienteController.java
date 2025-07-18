package com.tallerherramientas.tallerprueba.Controllers.PersonaController;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Cliente;
import com.tallerherramientas.tallerprueba.Services.Contratos.ClienteDAO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteController {
    //Declaracion de tipo de servicio
    @Autowired
    private ClienteDAO clienteDAO;

    @GetMapping("/all")
    public List<Cliente> listar(){
        return clienteDAO.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente>obtenerPorId(@PathVariable Long id){
        Cliente cliente = clienteDAO.obtenerPorId(id).orElse(null);
        if(cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> crear (@Valid @RequestBody Cliente cliente, BindingResult result){
        /*if (result.hasErrors()){
            Map<String,String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }*/
        Cliente nuevoCliente = clienteDAO.guardar(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente>actualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        Cliente existente = clienteDAO.obtenerPorId(id).orElse(null);
        if (existente == null){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        existente.setNombre(cliente.getNombre());
        existente.setApellido(cliente.getApellido());
        existente.setTipoCliente(cliente.getTipoCliente());
        existente.setTelefono(cliente.getTelefono());
        existente.setDireccion(cliente.getDireccion());

        return ResponseEntity.ok(clienteDAO.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Cliente cliente = clienteDAO.obtenerPorId(id).orElse(null);
        if(cliente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clienteDAO.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
