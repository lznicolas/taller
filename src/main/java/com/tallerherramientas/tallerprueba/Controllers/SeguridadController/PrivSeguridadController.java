package com.tallerherramientas.tallerprueba.Controllers.SeguridadController;

import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class PrivSeguridadController {
    @GetMapping("/priv")
    public String privado(){
        return "URL privadas";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        return "URL admin";
    }
}
