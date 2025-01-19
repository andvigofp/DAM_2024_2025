package org.example.repository;

import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.Scanner;

public class Altas {
    Session session;
    Sesiones sesiones;

    public Altas(Session session) {
        this.session = session;
        sesiones = new Sesiones(session);
    }

    public void alta(Scanner teclado) {
        byte op = 0;
        do {
            Cadenas.menuAltas();
            op = ControlData.leerByte(teclado);
            switch (op) {
                case 1:
                    empresa(teclado);
                    break;
                case 2:
                    empleado(teclado);
                    break;
                case 3:
                    producto(teclado);
                    break;
                case 4:
                    venta(teclado);
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

    private void empresa(Scanner teclado) {

        System.out.println("\nNUEVA EMPRESA");

        System.out.println("CIF");
        String cif = ControlData.leerString(teclado);

        Transaction trx = this.session.beginTransaction();
        Empresa empresa = (Empresa) session.get(Empresa.class, cif);
        trx.commit();

        if (!Objects.isNull(empresa)) {
            System.out.println("\nNO ES POSIBLE AÑADIR LA EMPRESA " + cif + ". YA ESTÁ REGISTARDA.");
            System.out.println("\n" + empresa);

        } else {

            System.out.println("NOMBRE:");
            String nombre = ControlData.leerString(teclado);

            System.out.println("DIRECCIÓN:");
            String direccion = ControlData.leerString(teclado);

            System.out.println("TELEFONO:");
            String telefono = ControlData.leerString(teclado);

            empresa = new Empresa(cif, nombre, direccion, telefono);

            this.sesiones.guardar(empresa);

            System.out.println("\nEMPRESA AÑADIDA");

        }
    }

    private void empleado(Scanner teclado) {

        byte op = 0;
        do {
            System.out.println("-----------------\n" + "\t\tALTAS");
            Cadenas.menuEmpleados();
            op = ControlData.leerByte(teclado);
            switch (op) {
                case 1:
                    fijo(teclado);
                    break;
                case 2:
                    temporal(teclado);
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

    private void producto(Scanner teclado) {

        System.out.println("\nNUEVO PRODUCTO");

        System.out.println("CÓDIGO:");
        String codigo = ControlData.leerString(teclado);

        Transaction trxTransaction  = this.session.beginTransaction();
        Producto producto = (Producto) session.get(Producto.class, codigo);
        trxTransaction.commit();

        if (!Objects.isNull(producto)) {
            System.out.println("\nNO ES POSIBLE AÑADIR EL PRODUCTO " + codigo + ". YA ESTÁ REGISTARDO.");
            System.out.println("\n" + producto);

        } else {

            System.out.println("STOCK:");
            int stock = ControlData.leerInt(teclado);
            if (stock < 30) {
                System.out.println("AVISO: EL STOCK MÍNIMO PARA PODER REALIZAR VENTAS ES DE 30 UNIDADES.\nESTE TENDRÁ QUE SER MODIFICADO EN EL FUTURO.\n");
            }
            System.out.println("PRECIO UNITARIO:");
            float precio = ControlData.leerFloat(teclado);

            producto = new Producto(codigo, stock, precio);

            System.out.println("EMPRESA A LA QUE SE ASOCIARÁ EL PRODUCTO (CIF):");
            String cif = ControlData.leerString(teclado);

            trxTransaction = this.session.beginTransaction();
            Empresa empresa = (Empresa) session.get(Empresa.class, cif);
            trxTransaction.commit();

            if (Objects.isNull(empresa)) {
                System.out.println("\nLA EMPRESA NO EXISTE");
                byte op = 0;
                do {
                    System.out.println("GUARDAR EL PRODUCTO SIN ASOCIARLO A NINGUNA EMPRESA:");
                    System.out.println("1.-SI");
                    System.out.println("2.-NO");
                    op = ControlData.leerByte(teclado);
                    switch (op) {
                        case 1:
                            this.sesiones.guardar(producto);
                            System.out.println("\nPRODUCTO AÑADIDO");
                            break;
                        case 2:
                            System.out.println("\nPRODUCTO NO AÑADIDO");
                            break;
                        default:
                            Cadenas.mesajeDefaultMenu();
                            break;
                    }
                } while (op != 2 && op != 1);

            } else {
                producto.setEmpresa(empresa);
                this.sesiones.guardar(producto);
                System.out.println("\nPRODUCTO AÑADIDO");
            }

        }
    }

    private void venta(Scanner teclado) {

        System.out.println("\nNUEVA VENTA");

        System.out.println("CÓDIGO DEL PRODUCTO:");
        String codigo = ControlData.leerString(teclado);

        Transaction trxTransaction = this.session.beginTransaction();
        Producto producto = (Producto) session.get(Producto.class, codigo);
        trxTransaction.commit();

        if (Objects.isNull(producto)) {
            System.out.println("\nEL PRODUCTO " + codigo + " NO EXISTE.");
        } else {
            Empresa empresa = producto.getEmpresa();
            if (Objects.isNull(empresa)) {
                System.out.println("\nEL PRODUCTO " + codigo + " NO TIENE NINGUNA EMPRESA ASOCIDA. NO SE PUEDE VENDER.");
            } else {

                System.out.println("DNI DEL EMPLEADO:");
                String dni = ControlData.leerDni(teclado);

                trxTransaction = this.session.beginTransaction();
                Temporal empleadoTemporal = (Temporal) session.get(Temporal.class, dni);
                trxTransaction.commit();

                if (Objects.isNull(empleadoTemporal)) {
                    System.out.println("\nEL EMPLEADO " + dni + " NO EXISTE.");
                } else {
                    Empresa empresaEmpleado = empleadoTemporal.getEmpresa();
                    if (Objects.isNull(empresaEmpleado) || !empresa.getCif().equals(empresaEmpleado.getCif())) {
                        System.out.println("\nEL EMPLEADO " + dni + " NO TRABAJA PARA LA EMPRESA QUE VENDE EL PRODUCTO " + codigo + ".\nNO SE PUEDE REALIZAR LA VENTA.");
                    } else {

                        Date fechaVenta = new java.sql.Date(Calendar.getInstance().getTime().getTime());

                        LocalTime now = LocalTime.now();
                        Time hora = Time.valueOf(now);

                        System.out.println("\nPRODUCTO\n" + producto);

                        System.out.println("NÚMERO DE UNIDADES:");
                        int unidades = ControlData.leerInt(teclado);

                        int unidadesDisponiblesVenta = producto.getStockActualAlmacen() - producto.getSTOCK_MINIMO();

                        if (unidades <= unidadesDisponiblesVenta) {
                            float importe = unidades * producto.getPrecioUnitario();

                            int unidadesRestantes = producto.getStockActualAlmacen() - unidades;
                            producto.setStockActualAlmacen(unidadesRestantes);

                            this.sesiones.actualizar(producto);

                            Venta venta = new Venta(producto, empleadoTemporal, fechaVenta, hora, unidades, importe);
                            this.sesiones.guardar(venta);
                            System.out.println("\nVENTA REALIZADA");

                        } else {
                            System.out.println("\nNO ES POSIBLE REALIZAR LA VENTA");
                            System.out.println("UNIDADES DISPONIBLES: " + unidadesDisponiblesVenta);

                        }
                    }
                }

            }
        }
    }

    private void fijo(Scanner teclado) {

        System.out.println("NUEVO EMPLEADO FIJO");

        System.out.println("DNI:");
        String dni = ControlData.leerDni(teclado);

        Transaction trxTransaction = this.session.beginTransaction();
        Fijo empleadoFijo = (Fijo) session.get(Empleado.class, dni);
        trxTransaction.commit();

        if (!Objects.isNull(empleadoFijo)) {
            System.out.println("\nNO ES POSIBLE AÑADIR AL EMPLEADO " + dni + ". YA ESTÁ REGISTARDO.");
            System.out.println(empleadoFijo);

        } else {
            System.out.println("NOMBRE:");
            String nombre = ControlData.leerString(teclado);

            System.out.println("TELEFONO:");
            String telefono = ControlData.leerString(teclado);

            System.out.println("PORCENTAJE RETENCIÓN:");
            float retencion = ControlData.leerFloat(teclado);

            System.out.println("SALARIO BASE:");
            int salario = ControlData.leerInt(teclado);

            System.out.println("TRIENIOS:");
            int trienios = ControlData.leerInt(teclado);

            empleadoFijo = new Fijo(dni, nombre, telefono, retencion, salario, trienios);
            empleadoFijo.calcularNomina();

            System.out.println("EMPRESA PARA LA QUE TRABAJARÁ (CIF):");
            String cif = ControlData.leerString(teclado);

            trxTransaction = this.session.beginTransaction();
            Empresa empresa = (Empresa) session.get(Empresa.class, cif);
            trxTransaction.commit();

            if (Objects.isNull(empresa)) {
                System.out.println("LA EMPRESA NO EXISTE");
                byte op = 0;
                do {
                    System.out.println("GUARDAR EL EMPLEADO SIN EMPRESA ASOCIADA:");
                    System.out.println("1.-SI");
                    System.out.println("2.-NO");
                    op = ControlData.leerByte(teclado);
                    switch (op) {
                        case 1:
                            this.sesiones.guardar(empleadoFijo);
                            System.out.println("\nEMPLEADO FIJO AÑADIDO");
                            break;
                        case 2:
                            System.out.println("\nEMPLEADO FIJO NO AÑADIDO");
                            break;
                        default:
                            Cadenas.mesajeDefaultMenu();
                            break;
                    }
                } while (op != 2 && op != 1);

            } else {
                empleadoFijo.setEmpresa(empresa);
                this.sesiones.guardar(empleadoFijo);
                System.out.println("\nEMPLEADO FIJO AÑADIDO");
            }
        }
    }

    private void temporal(Scanner teclado) {

        System.out.println("NUEVO EMPLEADO TEMPORAL");

        System.out.println("DNI:");
        String dni = ControlData.leerDni(teclado);

        Transaction trxTransaction = this.session.beginTransaction();
        Temporal empleadoTemporal = (Temporal) session.get(Empleado.class, dni);
        trxTransaction.commit();

        if (!Objects.isNull(empleadoTemporal)) {
            System.out.println("\nNO ES POSIBLE AÑADIR AL EMPLEADO " + dni + ". YA ESTÁ REGISTARDO.");
            System.out.println(empleadoTemporal);

        } else {
            System.out.println("NOMBRE:");
            String nombre = ControlData.leerString(teclado);

            System.out.println("TELEFONO:");
            String telefono = ControlData.leerString(teclado);

            System.out.println("PORCENTAJE RETENCIÓN:");
            float retencion = ControlData.leerFloat(teclado);

            Date dateInicio;
            Date dateFin;
            do {
                System.out.println("FECHA DE INICIO (aaaa-mm-dd):");
                String fechaInicio = ControlData.leerFecha(teclado);
                dateInicio = Date.valueOf(fechaInicio);

                System.out.println("FECHA FIN (aaaa-mm-dd):");
                String fechaFin = ControlData.leerFecha(teclado);
                dateFin = Date.valueOf(fechaFin);

                if (dateInicio.after(dateFin)) {
                    System.out.println("LA FECHA DE INICIO DEBE SER ANTERIOR A LA FECHA FIN\n");
                }

            } while (dateInicio.after(dateFin));

            System.out.println("PAGO POR DÍA:");
            float pago = ControlData.leerFloat(teclado);

            empleadoTemporal = new Temporal(dni, nombre, telefono, retencion, dateInicio, dateFin, pago);
            empleadoTemporal.calcularNomina();

            System.out.println("EMPRESA PARA LA QUE TRABAJARÁ (CIF):");
            String cif = ControlData.leerString(teclado);

            trxTransaction = this.session.beginTransaction();
            Empresa empresa = (Empresa) session.get(Empresa.class, cif);
            trxTransaction.commit();

            if (Objects.isNull(empresa)) {
                System.out.println("LA EMPRESA NO EXISTE");
                byte op = 0;
                do {
                    System.out.println("GUARDAR EL EMPLEADO SIN EMPRESA ASOCIADA:");
                    System.out.println("1.-SI");
                    System.out.println("2.-NO");
                    op = ControlData.leerByte(teclado);
                    switch (op) {
                        case 1:
                            this.sesiones.guardar(empleadoTemporal);
                            System.out.println("\nEMPLEADO TEMPORAL AÑADIDO");
                            break;
                        case 2:
                            System.out.println("\nEMPLEADO TEMPORAL NO AÑADIDO");
                            break;
                        default:
                            Cadenas.mesajeDefaultMenu();
                            break;
                    }
                } while (op != 2 && op != 1);

            } else {
                empleadoTemporal.setEmpresa(empresa);
                this.sesiones.guardar(empleadoTemporal);
                System.out.println("\nEMPLEADO TEMPORAL AÑADIDO");
            }
        }
    }
}
