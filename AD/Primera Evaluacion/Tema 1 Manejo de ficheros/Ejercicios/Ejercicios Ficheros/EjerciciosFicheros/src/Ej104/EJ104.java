package Ej104;

import java.io.IOException;

/**
 * Desarrolla un programa Java que permita:
 *
 * Crear un fichero de texto de forma secuencial en el que vaya el siguiente texto:
 *
 * Ejemplo de escritura en un fichero de texto o txt
 *
 * Visualizar el contenido del fichero
 */
public class EJ104 {
    public static void main(String[] args) throws IOException {
        MEJ104 fichero = new MEJ104();

        //Nombre del fichero
        String nuevoFichero ="./src/Ej104/fichero.txt";

        //Contenido que se va escribir el fichero
        String contenido = "Ejemplo de escrictura en un fichero de texto o txt";

        //Crear y escribir el fichero
        fichero.escrictura(nuevoFichero, contenido);

        //Leer y visualizar el contenido del fichero
        fichero.leerFichero(nuevoFichero);
    }
}
