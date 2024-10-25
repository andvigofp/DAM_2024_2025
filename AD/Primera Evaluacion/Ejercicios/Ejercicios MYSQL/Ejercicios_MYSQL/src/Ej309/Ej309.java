package Ej309;

import java.util.ArrayList;
import java.util.Scanner;

public class Ej309 {
    public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);

            Operaciones operations = new Operaciones();

            final String menu = "1. PRESTAR UN LIBRO."
                    + "\n2. DEVOLVER UN LIBRO."
                    + "\n3. LISTAR LOS LIBROS PRESTADOS."
                    + "\n4. LISTA LOS LIBROS QUE PUEDEN SER PRESTADOS."
                    + "\n5. SALIR.";

            int opcion = -1;

            while (true) {
                System.out.println(menu);
                opcion = teclado.nextInt();

                if (opcion == 1) { // 1. PRESTAR UN LIBRO.
                    System.out.println("Introduce tu número de usuario");
                    String idUsuario = teclado.next();
                    System.out.println("Introduzca el código del libro que quieres coger:");
                    String codigo = teclado.next();

                    operations.addLoan(codigo, idUsuario);


                } else if (opcion == 2) { // 2. DEVOLVER UN LIBRO.
                    System.out.println("Introduzca el código del libro que quiere devolver:");
                    String codigo = teclado.next();

                    operations.addReturn(codigo);

                } else if (opcion == 3) { // 3. LISTAR LOS LIBROS PRESTADOS.
                    ArrayList<Libro> listaLibros = operations.borrowedBooksList();

                    for (Libro l : listaLibros)
                        System.out.println(l.toString());

                } else if (opcion == 4) { // 4. LISTA LOS LIBROS QUE PUEDEN SER PRESTADOS.
                    ArrayList<Libro> listaLibros = operations.availableBooksList();

                    for (Libro l : listaLibros)
                        System.out.println(l.toString());

                } else if (opcion == 5) { // 5. SALIR.
                    operations.closeConnection();

                    System.out.println("Adios!");
                    break;
                }
            }
        }
    }


