package org.example;

import org.example.entity.Cliente;
import org.example.repository.AlquilerRepositorio;
import org.example.repository.ClienteRepositorio;
import org.hibernate.Session;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main(String[] args) {
        System.out.println("Ejercicio 305");

        Session session = HibernateUtil.get().openSession();

        ClienteRepositorio clienteRepositorio = new ClienteRepositorio(session);
        AlquilerRepositorio alquilerRepositorio = new AlquilerRepositorio(session);

        Cliente cliente = clienteRepositorio.obtenerCiente(2);
        System.out.println(cliente.toString());

        Cliente nuevoCliente = new Cliente("36147672K", "Miguel", "miguel@gamil.com");
        clienteRepositorio.anhadirCliente(nuevoCliente);
        System.out.println(nuevoCliente.toString());

        nuevoCliente.setDni("36167898J");
        clienteRepositorio.modicarCliente(nuevoCliente);
        System.out.println(nuevoCliente.toString());

        clienteRepositorio.borrarCliente(nuevoCliente);

        //Ya está prestado daría error
        alquilerRepositorio.alquilar(3, 1);

        alquilerRepositorio.devolver(3);

        //No daría error porque ahora ya se ha devuelto
        alquilerRepositorio.alquilar(3, 1);
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
