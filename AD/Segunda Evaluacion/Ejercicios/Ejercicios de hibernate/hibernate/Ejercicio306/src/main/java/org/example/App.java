package org.example;

import org.example.entity.Cliente;
import org.example.repository.ClienteRepositorio;
import org.example.repository.LineaPedidoRepositorio;
import org.example.repository.PedidoRepositorio;
import org.hibernate.Session;

import java.util.Scanner;

/**
 * Hello world!
 *
 */

public class App {

    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Ejercicio 306");

        Session session = HibernateUtil.get().openSession();

        LineaPedidoRepositorio lineaPedidoRepositorio = new LineaPedidoRepositorio(session);
        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(session);
        PedidoRepositorio pedidoRepositorio = new PedidoRepositorio(session, teclado);

        int opcion = -1;

        do {
            opcion = pintarPedirInt("1. Mostrar todos los pedidos\n2. Mostrar pedidos de un cliente\n"
                    + "3. Añadir pedido\n4. Borrar pedido\n5. Salir");

            switch (opcion) {
                case 1: {
                    lineaPedidoRepositorio.mostrarTodosPedidos();
                    break;
                }
                case 2: {
                    String dniCliente = pintarPedirString("Introduzca el DNI del cliente");
                    Cliente cliente = clienteRepositorio.obtenerClientePorDNI(dniCliente);
                    lineaPedidoRepositorio.mostrarPedidosCliente(cliente);
                    break;
                }
                case 3: {
                    String dniCliente = pintarPedirString("Introduzca el DNI del cliente");
                    Cliente cliente = clienteRepositorio.obtenerClientePorDNI(dniCliente);

                    if (cliente.getIdCliente() != -1)
                        pedidoRepositorio.addPedido(cliente);
                    else
                        System.out.println("No existe un cliente con ese ID");
                    break;
                }
                case 4: {
                    int idPedido = pintarPedirInt("Introduzca el id del pedido");
                    pedidoRepositorio.eliminarPedido(idPedido);
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

    public static int pintarPedirInt(String mensaje) {
        System.out.println(mensaje);
        return teclado.nextInt();
    }

    public static String pintarPedirString(String mensaje) {
        System.out.println(mensaje);
        return teclado.next();
    }
}
