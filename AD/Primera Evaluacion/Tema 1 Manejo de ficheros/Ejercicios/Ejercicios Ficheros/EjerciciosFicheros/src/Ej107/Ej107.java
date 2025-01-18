package Ej107;

import java.util.Scanner;

/**
 * Desarrolla un programa Java que:
 *
 * Tenga el método clasificarDirectorio(String ruta): lista el contenido del directorio indicado, indicando si los elementos de la lista son ficheros o subdirectorios. En el caso de los ficheros, deben aparecer clasificados según su extensión.
 *
 * Tenga un método main que permita comprobar el funcionamiento del método anterior.
 */
public class Ej107 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        //Solicita la ruta del directorio al usuario
        System.out.println("Introduce la ruta del directorio: ");
        String ruta = teclado.nextLine();

        ME107.clasificarDirectorio(ruta);
    }
}
