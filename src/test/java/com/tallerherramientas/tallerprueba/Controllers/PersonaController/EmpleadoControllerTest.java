package com.tallerherramientas.tallerprueba.Controllers.PersonaController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import com.tallerherramientas.tallerprueba.Modelo.Enums.Especialidad;
import com.tallerherramientas.tallerprueba.Services.Contratos.EmpleadoDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmpleadoController.class)
@AutoConfigureMockMvc(addFilters = false)
class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoDAO empleadoDAO;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Devuelve lista de especialidades")
    void listarEspecialidades() throws Exception {
        when(empleadoDAO.listarEspecialidades()).thenReturn(Arrays.asList(Especialidad.values()));

        mockMvc.perform(get("/api/empleados/especialidades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("RECTIFICADOR"));
    }

    @Test
    @DisplayName("Devuelve legajo sugerido OK")
    void legajoSugeridoOk() throws Exception {
        when(empleadoDAO.obtenerSiguienteLegajo(Especialidad.ADMINISTRATIVO)).thenReturn(101);

        mockMvc.perform(get("/api/empleados/legajos/sugerido")
                        .param("especialidad", "ADMINISTRATIVO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(101));
    }

    @Test
    @DisplayName("Legajo sugerido falla con especialidad inválida")
    void legajoSugeridoBadRequest() throws Exception {
        mockMvc.perform(get("/api/empleados/legajos/sugerido")
                        .param("especialidad", "INVALIDA"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Crear empleado aplica ajuste de legajo")
    void crearEmpleadoAjustaLegajo() throws Exception {
        Empleado empleado = new Empleado();
        empleado.setNombre("Test");
        empleado.setApellido("User");
        empleado.setEspecialidad(Especialidad.ADMINISTRATIVO);
        empleado.setLegajo("10");
        empleado.setSueldo(BigDecimal.valueOf(1000));

        Empleado guardado = new Empleado();
        guardado.setId(1L);
        guardado.setNombre(empleado.getNombre());
        guardado.setApellido(empleado.getApellido());
        guardado.setEspecialidad(empleado.getEspecialidad());
        guardado.setLegajo("100"); // ajustado

        when(empleadoDAO.ajustarLegajoSegunRegla(any(Empleado.class))).thenReturn("100");
        when(empleadoDAO.guardar(any(Empleado.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.legajo").value("100"));
    }
}
