package Ej309;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Se pide crear un programa Java que tenga un paquete llamado dataBase que tenga dos clases:
 *
 * Clase Operaciones.java: que se encargará de la conexión a la base de datos y contendrá las siguientes funciones:
 *
 * existsClient: recibirá un id de cliente (idClient) y devolverá true o false indicando si el cliente existe en la BD o no.
 * existsBook: recibe un id de libro (idBook) y devuelve true o false indicando si el libro existe en la BD o no.
 * isBorrowed: recibe el código (code) de un libro y devuelve true o false si el libro está prestado o no.
 * addLoan: recibe el código (code) de un libro y el id de cliente (idClient) y usa los métodos anteriores para hacer las comprobaciones necesarias y añadir un préstamos a la BD.
 * addReturn: recibe el código de un libro y modifica el campo prestado (borrowed) en la tabla de préstamos (loan).
 * borrowedBooksList: devuelve un ArrayList de libros con la lista de libros prestados hasta el momento.
 * availableBooksList: devuelve un ArrayList de libros con la lista de libros que están disponibles para ser prestados.
 * Clase Main.java: permitirá lanzar la aplicación, mostrará un menú que permita:
 *
 * Prestar un libro.
 * Devolver un libro.
 * Listar los libros prestados.
 * Listar los libros que se pueden prestar.
 * NOTA: tendrán que notificarse todos los posibles errores.
 */
public class Ej309 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        Operaciones operations = new Operaciones();

        MEj309 metodos = new MEj309();

        final String menu = "1. PRESTAR UN LIBRO."
                + "\n2. DEVOLVER UN LIBRO."
                + "\n3. LISTAR LOS LIBROS PRESTADOS."
                + "\n4. LISTA LOS LIBROS QUE PUEDEN SER PRESTADOS."
                + "\n5. SALIR.";

        int opcion = -1;

        while (true) {
            System.out.println(menu);
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1: // 1. PRESTAR UN LIBRO.
                    metodos.prestar(teclado, operations);
                    break;
                case 2: // 2. DEVOLVER UN LIBRO.
                    metodos.devolverLibroPrestado(teclado, operations);
                    break;
                case 3: // 3. LISTAR LOS LIBROS PRESTADOS.
                    metodos.listarLibroPrestados(operations);
                    break;
                case 4: // 4. LISTA LOS LIBROS QUE PUEDEN SER PRESTADOS.
                    metodos.listarTodosLibrosPuedenPrestar(operations);
                    break;
                case 5: // Fin del programa
                    metodos.desconectar(operations);

                    teclado.close();
                    return;
                default:
                    System.out.println("Opción no válida, por favor elija una opción del menú entre 1-5");
            }
        }
    }
    }


