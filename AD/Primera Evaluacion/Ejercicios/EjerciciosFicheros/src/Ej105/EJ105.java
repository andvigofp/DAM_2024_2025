package Ej105;

import java.io.IOException;

/**
 * Desarrolla un programa Java que defina una clase ManejoFicheros que implemente los siguientes métodos:
 *
 * void crearFichero(String fichero): crea el fichero indicado
 *
 * void borrarFichero(String fichero): borra el fichero indicado.
 *
 * void crearDirectorio(String ruta): crea el directorio indicado.
 *
 * void borrarDirectorio(String ruta): borra el directorio indicado.
 *
 * void listarDirectorio(String ruta): lista el contenido del directorio
 *
 * Clase main que permita comprobar el funcionamiento de los métodos anteriores.
 *
 * Nota: usa la clase File para realizar el ejercicio
 */

public class EJ105 {
    public static void main(String[] args) throws IOException {
        ManejoFicheros ficheros = new ManejoFicheros();
        ficheros.crearDirectorio("./src/directorio");
        ficheros.crearFichero("./src/directorio/fichero.txt");
        ficheros.listarDirectrio("./src/directorio");
        //ficheros.borrarFichero("./src/directorio/fichero.txt");
        ficheros.listarDirectrio("./src/directorio");
        ficheros.borrarDirectorio("./src/directorio");
    }
}
