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
            cliente.setCuilt("20-38777888-7");
            cliente.setTelefono("03816345678");
            cliente.setTipoCliente(TipoCliente.FRECUENTE);

            //Empleado
            Empleado emp1= new Empleado(null,"38555333","27-38555333-0","Julia","Morales",new BigDecimal("800000"), Especialidad.RECTIFICADOR);
            Empleado emp2= new Empleado(null,"38111222","20-38111222-0","Javier","Montalvo",new BigDecimal("20000"),Especialidad.MANTENIMIENTO );

            Repuesto repuesto1 = new Repuesto();
            repuesto1.setCodigoDeProducto("AAA001");
            repuesto1.setTitulo("Piston");
            repuesto1.setDescripcion("Piston del motor");
            repuesto1.setUbicacion(Ubicacion.BAJA);

            Repuesto repuesto2 = new Repuesto();
            repuesto2.setCodigoDeProducto("BBB002");
            repuesto2.setTitulo("Valvula");
            repuesto2.setDescripcion("Valvula de adminsion");
            repuesto2.setUbicacion(Ubicacion.BAJA);

            //trabajo
            Trabajo trabajo = new Trabajo();
            trabajo.setTipoTrabajo(TipoTrabajo.RECTIFICAION);
            trabajo.setDiagnostico("Motor sin compresion");
            trabajo.setTareasRealizar("Reemplazo de pistones");
            trabajo.setDetalles("Se entrega con Pruebas de compresion");
            trabajo.setEstado(EstadoTrabajo.EN_PROGRESO);

            //Detalles de repuestos
            DetalleRepuestoTrabajo dr1 = new DetalleRepuestoTrabajo();
            dr1.setTrabajo(trabajo);
            dr1.setRepuesto(repuesto1);
            dr1.setCantidadUsada(2);

            DetalleRepuestoTrabajo dr2 = new DetalleRepuestoTrabajo();
            dr2.setTrabajo(trabajo);
            dr2.setRepuesto(repuesto2);
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
            trabajo.setDetallesRepuestos(Arrays.asList(dr1,dr2));
            trabajo.setDetallesEmpleados(Arrays.asList(de1,de2));
            trabajo.setDetallesClientes(Collections.singletonList(dc1));

            //Mkostramos
            System.out.println(trabajo);
        };
    }
}
