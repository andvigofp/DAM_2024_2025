package org.example.repository;

import org.example.entity.Empleado;
import org.example.entity.Fijo;
import org.example.entity.Producto;
import org.example.entity.Temporal;
import org.hibernate.Session;

import java.util.Scanner;

public class Modificaciones {
    Session sesion;

    public Modificaciones(Session session) {
        this.sesion = session;
    }

    public void modificacion(Scanner teclado) {
        byte op = 0;
        do {
            Cadenas.menuModificaciones();
            op = ControlData.leerByte(teclado);
            switch (op) {
                case 1:
                    precioProducto(teclado);
                    break;
                case 2:
                    sueldoBaseEmpleado(teclado);
                    break;
                case 3:
                    porcentajeRetencion(teclado);
                    break;
                case 4:
                    importeDiaEmpleadoTemporal(teclado);
                    break;
                case 0:
                    Cadenas.vueltaAnteriorMenu();
                    break;
                default:
                    Cadenas.mesajeDefaultMenu();
                    break;
            }
        } while (op != 0);

    }

    private void precioProducto(Scanner teclado){
        System.out.println("Código del producto");
        String codigo = ControlData.leerString(teclado);
        System.out.println("Nuevo precio del producto");
        float nuevoPrecio = teclado.nextFloat();

        Producto producto = (Producto) this.sesion.get(Producto.class, codigo);
        if (producto == null) {
            System.out.println("No existe el producto");
        }else {
            producto.setPrecioUnitario(nuevoPrecio);
            this.sesion.update(producto);
        }
    }

    private void sueldoBaseEmpleado(Scanner teclado){
        System.out.println("DNI del empleado");
        String dni = ControlData.leerString(teclado);
        System.out.println("Nuevo sueldo base");
        int nuevoPrecio = teclado.nextInt();

        Fijo fijo = (Fijo) this.sesion.get(Fijo.class, dni);
        if (fijo == null) {
            System.out.println("No existe el empleado");
        }else {
            fijo.setSalarioBase(nuevoPrecio);
            this.sesion.update(fijo);
        }
    }

    private void porcentajeRetencion(Scanner teclado){
        System.out.println("DNI del empleado");
        String dni = ControlData.leerString(teclado);
        System.out.println("Nuevo porcentaje de retención");
        float porcentaje = teclado.nextFloat();

        Empleado empleado = (Empleado) this.sesion.get(Empleado.class, dni);
        if (empleado == null) {
            System.out.println("No existe el empleado");
        }else {
            empleado.setPorcentajeRetencion(porcentaje);
            this.sesion.update(empleado);
        }
    }

    private void importeDiaEmpleadoTemporal(Scanner teclado){
        System.out.println("DNI del empleado temporal");
        String dni = ControlData.leerString(teclado);
        System.out.println("Nuevo precio por día");
        float pagoDia = teclado.nextFloat();

        Temporal temporal = (Temporal) this.sesion.get(Temporal.class, dni);
        if (temporal == null) {
            System.out.println("No existe el empleado");
        }else {
            temporal.setPagoDia(pagoDia);
            this.sesion.update(temporal);
        }
    }

}

