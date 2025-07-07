package com.tallerherramientas.tallerprueba;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClienteTest {
    @Test
    void testClienteBuilder(){
        Cliente cliente = Cliente.builder()
                .id(11L)
                .nombre("Nicolas")
                .apellido("Lamas")
                .telefono("123123123")
                .build();
        assertEquals(11L,cliente.getId());

    }
}
