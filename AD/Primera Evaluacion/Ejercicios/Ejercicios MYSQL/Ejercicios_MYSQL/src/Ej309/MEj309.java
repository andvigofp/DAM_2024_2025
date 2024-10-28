package Ej309;

import java.util.ArrayList;
import java.util.Scanner;

public class MEj309 {
    public void prestar(Scanner teclado,Operaciones operaciones) {

        System.out.println("Introduce tu número de usuario");
        String idUsuario = teclado.next();
        System.out.println("Introduzca el código del libro que quieres coger:");
        String codigo = teclado.next();

        System.out.println("El libro a sido prestado con éxito");

        operaciones.prestarLibro(codigo, idUsuario);
    }

    public void devolverLibroPrestado(Scanner teclado, Operaciones operaciones) {
        System.out.println("Introduzca el código del libro que quiere devolver:");
        String codigo = teclado.next();

        operaciones.devolverLibro(codigo);
    }

    public void listarLibroPrestados(Operaciones operaciones) {
        ArrayList<Libro> listaLibros = operaciones.listarLibrosPrestado();

        for (Libro l : listaLibros)
            System.out.println(l.toString());
    }

    public void listarTodosLibrosPuedenPrestar(Operaciones operaciones) {
        ArrayList<Libro> listaLibros = operaciones.listaLibrosPuedePrestar();

        for (Libro l : listaLibros)
            System.out.println(l.toString());
    }

    public void desconectar(Operaciones operaciones) {
        operaciones.closeConnection();

        System.out.println("Fin del programa");
    }
}
