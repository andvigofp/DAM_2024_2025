package org.example;

import org.example.entity.Cliente;
import org.example.repository.ClienteRepositorio;
import org.example.repository.FacturaRepositorio;
import org.hibernate.Session;

import java.util.Scanner;

/**
 * Hello world!
 *
 */

public class App {

    static Scanner teclado;

    public static void main(String[] args) {
        System.out.println("Ejercicio 307");

        Session session = HibernateUtil.get().openSession();

        teclado = new Scanner(System.in);

        FacturaRepositorio facturaRepositorio = new FacturaRepositorio(session, teclado);
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(session);

        int opcion = -1;

        do {
            opcion = pedirInt("1. Mostrar todas las facturas\n2. Añadir factura\n"
                    + "3. Modificar factura\n4. Borrar factura\n5. Salir");

            switch (opcion) {
                case 1: {
                    facturaRepositorio.mostrarTodasFacturas();
                    break;
                }
                case 2: {
                    int idCliente= pedirInt("Introduzca el ID del cliente");

                    Cliente cliente = clienteRepositorio.getClientePorId(idCliente);
                    facturaRepositorio.addFactura(cliente);
                    break;
                }
                case 3: {
                    facturaRepositorio.modificarFactura();
                    break;
                }
                case 4: {
                    facturaRepositorio.borrarFactura();
                    break;
                }
                case 5: {
                    System.exit(0);
                    break;
                }
            }
        }while (opcion != 5);

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    private static int pedirInt(String string) {
        System.out.println(string);
        return teclado.nextInt();
    }

    private Double pedirDouble(String string) {
        System.out.println(string);
        return teclado.nextDouble();
    }

    private String pedirString(String string) {
        System.out.println(string);
        return teclado.next();
    }
}
