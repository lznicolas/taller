package com.tallerherramientas.tallerprueba.Controllers.EmpresaConfigController;

import com.tallerherramientas.tallerprueba.Modelo.Entities.EmpresaConfig;
import com.tallerherramientas.tallerprueba.Services.Contratos.EmpresaConfigDAO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresa-config")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpresaConfigController {
    @Autowired
    private EmpresaConfigDAO empresaConfigDAO;

    @GetMapping
    public ResponseEntity<EmpresaConfig> obtenerPrimera() {
        return empresaConfigDAO.obtenerPrimera()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmpresaConfig>> listar() {
        List<EmpresaConfig> lista = empresaConfigDAO.listar();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaConfig> obtenerPorId(@PathVariable Long id) {
        EmpresaConfig config = empresaConfigDAO.obtenerPorId(id).orElse(null);
        if (config == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(config);
    }

    @PostMapping
    public ResponseEntity<EmpresaConfig> crear(@Valid @RequestBody EmpresaConfig empresaConfig) {
        EmpresaConfig creada = empresaConfigDAO.guardar(empresaConfig);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaConfig> actualizar(@PathVariable Long id, @Valid @RequestBody EmpresaConfig empresaConfig) {
        EmpresaConfig existente = empresaConfigDAO.obtenerPorId(id).orElse(null);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setNombreFantasia(empresaConfig.getNombreFantasia());
        existente.setRazonSocial(empresaConfig.getRazonSocial());
        existente.setCuit(empresaConfig.getCuit());
        existente.setDireccion(empresaConfig.getDireccion());
        existente.setTelefono(empresaConfig.getTelefono());
        existente.setCorreoElectronico(empresaConfig.getCorreoElectronico());
        existente.setLogo(empresaConfig.getLogo());
        existente.setMensajePie(empresaConfig.getMensajePie());
        existente.setCondicionIva(empresaConfig.getCondicionIva());

        return ResponseEntity.ok(empresaConfigDAO.guardar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        EmpresaConfig existente = empresaConfigDAO.obtenerPorId(id).orElse(null);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        empresaConfigDAO.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
