package com.tallerherramientas.tallerprueba.Controllers.SeguridadController;

import com.tallerherramientas.tallerprueba.Modelo.DTO.UsuarioDTO;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Seguridad.Usuario;
import com.tallerherramientas.tallerprueba.Repositories.UsuarioRepository;
import com.tallerherramientas.tallerprueba.Services.Contratos.ServicioGenericoDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicSeguridadController {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String home(){
        return "home Public";
    }

    @Transactional
    @PostMapping("/createuser")
    public ResponseEntity<String>registrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        if(usuarioRepository.findByUsername(usuarioDTO.getUsername()) != null){
            return ResponseEntity.badRequest().body("Error: El usuario ya existe.");

        }
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(usuarioDTO.getUsername());
        nuevoUsuario.setRole(usuarioDTO.getRole());

        String passwordCifrada=passwordEncoder.encode(usuarioDTO.getPassword());
        nuevoUsuario.setPassword(passwordCifrada);

        usuarioRepository.save(nuevoUsuario);

        return ResponseEntity.ok("Usuario creado correctamente");
    }
}
