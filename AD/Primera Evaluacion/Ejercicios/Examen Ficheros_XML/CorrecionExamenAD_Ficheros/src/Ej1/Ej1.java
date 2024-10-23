package Ej1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

/**
 * Realiza un programa en Java que filtre los diferentes ficheros de un directorio. Para ello,
 * el programa debe solicitar por consola al usuario que introduzca una cadena de texto. El
 * filtro mostrará por consola los nombres de aquellos ficheros del directorio que contengan
 * esa cadena en su nombre.
 * Se tendrán que utilizar los elementos de programación que se han visto en esta unidad
 * para este propósito.
 * Por ejemplo, si el usuario introduce la cadena “tal”, el filtro debería mostrar un fichero que
 * se llame “pantalla.txt”, pero no uno que se llame “ratón.txt”, porque no contiene la cadena
 * “tal” dentro de su nombre.
 */

public class Ej1 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        File directorio = new File("./src/Ej1/Pruebas");

        System.out.println("Introduce el filtro que desa buscar");
        String filtro = teclado.nextLine().trim();

        FilenameFilter filenameFilter = new FilExstension(filtro);

        File[] todosArchivos = directorio.listFiles(filenameFilter);

        if (directorio.exists() && todosArchivos.length > 0) {
            for (File extension : todosArchivos) {
                System.out.println(extension);
            }
        }
    }
}
