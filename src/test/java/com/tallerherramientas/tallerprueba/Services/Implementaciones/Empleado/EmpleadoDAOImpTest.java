package com.tallerherramientas.tallerprueba.Services.Implementaciones.Empleado;

import com.tallerherramientas.tallerprueba.Modelo.Entities.Empleado;
import com.tallerherramientas.tallerprueba.Modelo.Enums.Especialidad;
import com.tallerherramientas.tallerprueba.Repositories.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmpleadoDAOImpTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    private EmpleadoDAOImp empleadoDAO;

    @BeforeEach
    void setup() {
        empleadoDAO = new EmpleadoDAOImp(empleadoRepository);
    }

    @Test
    void obtenerSiguienteLegajo_adminParteEn100() {
        when(empleadoRepository.findMaxLegajoByEspecialidad(Especialidad.ADMINISTRATIVO)).thenReturn(null);

        Integer siguiente = empleadoDAO.obtenerSiguienteLegajo(Especialidad.ADMINISTRATIVO);

        assertEquals(100, siguiente);
    }

    @Test
    void obtenerSiguienteLegajo_noAdminParteEn10000() {
        when(empleadoRepository.findMaxLegajoByEspecialidad(Especialidad.MANTENIMIENTO)).thenReturn(null);

        Integer siguiente = empleadoDAO.obtenerSiguienteLegajo(Especialidad.MANTENIMIENTO);

        assertEquals(10000, siguiente);
    }

    @Test
    void obtenerSiguienteLegajo_incrementaSegunMax() {
        when(empleadoRepository.findMaxLegajoByEspecialidad(Especialidad.ADMINISTRATIVO)).thenReturn(150);
        when(empleadoRepository.findMaxLegajoByEspecialidad(Especialidad.SOLDADOR)).thenReturn(12050);

        assertEquals(151, empleadoDAO.obtenerSiguienteLegajo(Especialidad.ADMINISTRATIVO));
        assertEquals(12051, empleadoDAO.obtenerSiguienteLegajo(Especialidad.SOLDADOR));
    }

    @Test
    void ajustarLegajoSegunRegla_corrigeLegajoInvalido() {
        when(empleadoRepository.findMaxLegajoByEspecialidad(Especialidad.MANTENIMIENTO)).thenReturn(10020);
        Empleado empleado = Empleado.builder()
                .nombre("Juan")
                .apellido("Perez")
                .especialidad(Especialidad.MANTENIMIENTO)
                .legajo("9000")
                .sueldo(BigDecimal.valueOf(1000))
                .build();

        String ajustado = empleadoDAO.ajustarLegajoSegunRegla(empleado);

        assertEquals("10021", ajustado);
        assertEquals("10021", empleado.getLegajo());
    }

    @Test
    void ajustarLegajoSegunRegla_respetaLegajoValido() {
        when(empleadoRepository.findMaxLegajoByEspecialidad(Especialidad.ADMINISTRATIVO)).thenReturn(120);
        Empleado empleado = Empleado.builder()
                .nombre("Ana")
                .apellido("Lopez")
                .especialidad(Especialidad.ADMINISTRATIVO)
                .legajo("150")
                .sueldo(BigDecimal.valueOf(1200))
                .build();

        String ajustado = empleadoDAO.ajustarLegajoSegunRegla(empleado);

        assertEquals("150", ajustado);
    }
}
