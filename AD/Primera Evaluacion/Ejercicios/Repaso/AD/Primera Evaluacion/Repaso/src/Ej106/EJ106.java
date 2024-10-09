package Ej106;

import java.util.Scanner;

/**
 * Desarrolla un programa en Java permita crear una lista de contactos que puedes agregar, eliminar y mostrar. Cada contacto estará representado por una clase Contacto.
 *
 * El programa implementará un menú que permita:
 *
 * Agregar contacto
 * Mostrar contacto
 * Eliminar contacto
 */

public class EJ106 {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        do {
            System.out.println("Menú: ");
            System.out.println("1. Agregar contacto:");
            System.out.println("2. Mostrar contactos:");
            System.out.println("3. Eliminar contacto:");
            System.out.println("4. Salir:");
            System.out.println("Elige una opción: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    agenda.agregarContacto();
                    break;
                case 2:
                    agenda.mostrarConatctos();
                    break;
                case 3:
                    agenda.eliminarContacto();
                    break;
                case 4:
                    salir=true;
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. por favor, elige una opción del 1 al 4.");
            }
        }while (!salir);
    }
}
