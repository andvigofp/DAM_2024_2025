package Ej106;

import java.util.Scanner;

/**
 * Utilizando la interfaz FilenameFilter realiza los siguientes apartados:
 *
 * Crear una clase FiltrarNombre que implemente el siguiente método:
 *
 * filtrar(String ruta, String extension): lista solo aquellos archivos de la ruta que tienen una determinada extension
 * Crear una clase FiltrarTamano que implemente el siguiente método:
 *
 * filtrar(String ruta, float minTamano): lista solo aquellos archivos de la ruta que tienen un tamaño mayor que el especificado.
 * Realizar un programa main que permita comprobar el funcionamiento de los métodos anteriores
 */
public class Ej106 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        //Pedir la ruta al usuario
        System.out.println("Introduce la ruta del directorio: ");
        String ruta = teclado.nextLine();

        // Filtrar archivos por extensión
        System.out.println("Introduce la extensión para filtrar ");
        String extensión = teclado.nextLine();
        FiltrarNombre.filtrar(ruta, extensión);

        //Filtrar archivos por tamaño
        System.out.println("Introduce el tamaño minímo (en bytes) para filtrar: ");
        float tamano = teclado.nextFloat();
        FiltrarTamano.filtrar(ruta, tamano);
    }
}
