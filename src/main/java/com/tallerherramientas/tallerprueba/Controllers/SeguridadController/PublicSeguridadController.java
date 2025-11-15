package com.tallerherramientas.tallerprueba.Controllers.SeguridadController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicSeguridadController {
    @GetMapping("/home")
    public String home(){
        return "home Public";
    }
}
