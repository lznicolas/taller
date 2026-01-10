package com.tallerherramientas.tallerprueba;

import com.tallerherramientas.tallerprueba.Modelo.Entities.*;
import com.tallerherramientas.tallerprueba.Modelo.Enums.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class TestRunner {

    @Bean
    public CommandLineRunner runTest(){

        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNombre("Nicolas");
            cliente.setApellido("Lamas");
            cliente.setDni("38777888");
            cliente.setCuil("20-38777888-7");
            cliente.setTelefono("03816345678");
            cliente.setTipoCliente(TipoCliente.FRECUENTE);

            //Empleado
            Empleado emp1= new Empleado(null,"38555333","27-38555333-0","Julia","Morales",new BigDecimal("800000"), Especialidad.RECTIFICADOR);
            Empleado emp2= new Empleado(null,"38111222","20-38111222-0","Javier","Montalvo",new BigDecimal("20000"),Especialidad.MANTENIMIENTO );

            Articulo articulo1 = new Articulo();
            articulo1.setCodigoDeProducto("AAA001");
            articulo1.setTitulo("Piston");
            articulo1.setDescripcion("Piston del motor");
            articulo1.setUbicacion(Ubicacion.BAJA);

            Articulo articulo2 = new Articulo();
            articulo2.setCodigoDeProducto("BBB002");
            articulo2.setTitulo("Valvula");
            articulo2.setDescripcion("Valvula de adminsion");
            articulo2.setUbicacion(Ubicacion.BAJA);

            //trabajo
            Trabajo trabajo = new Trabajo();
            trabajo.setTipoTrabajo(TipoTrabajo.RECTIFICACION);
            trabajo.setDiagnostico("Motor sin compresion");
            trabajo.setTareasRealizar("Reemplazo de pistones");
            trabajo.setDetalles("Se entrega con Pruebas de compresion");
            trabajo.setEstado(EstadoTrabajo.EN_PROGRESO);

            //Detalles de articulos
            DetalleArticuloTrabajo dr1 = new DetalleArticuloTrabajo();
            dr1.setTrabajo(trabajo);
            dr1.setArticulo(articulo1);
            dr1.setCantidadUsada(2);

            DetalleArticuloTrabajo dr2 = new DetalleArticuloTrabajo();
            dr2.setTrabajo(trabajo);
            dr2.setArticulo(articulo2);
            dr2.setCantidadUsada(4);

            //detalles de empleados
            DetalleEmpleadoTrabajo de1= new DetalleEmpleadoTrabajo();
            de1.setTrabajo(trabajo);
            de1.setEmpleado(emp1);

            DetalleEmpleadoTrabajo de2= new DetalleEmpleadoTrabajo();
            de2.setTrabajo(trabajo);
            de2.setEmpleado(emp2);

            //Detalle de cliente
            DetalleClienteTrabajo dc1= new DetalleClienteTrabajo();
            dc1.setTrabajo(trabajo);
            dc1.setCliente(cliente);

            //Asociamos al trabajo
            trabajo.setDetallesArticulos(Arrays.asList(dr1,dr2));
            trabajo.setDetallesEmpleados(Arrays.asList(de1,de2));
            trabajo.setDetallesClientes(Collections.singletonList(dc1));

            //Mkostramos
            System.out.println(trabajo);
        };
    }
}
