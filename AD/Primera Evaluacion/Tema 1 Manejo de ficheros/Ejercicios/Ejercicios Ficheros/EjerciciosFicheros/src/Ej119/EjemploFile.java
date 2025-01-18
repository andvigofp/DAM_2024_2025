package Ej119;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

/**
 * Desarrolla un clase en Java llamada EjemplosFile que mediante el uso de la clase java.io.File implemente cuatro métodos:
 *
 * crearFichero(String ruta): permite crear un archivo nuevo
 * borrarFichero(String ruta): borra un archivo
 * copiar(String origen, String destino): crea un nuevo archivo y copia el contenido de uno en otro
 * getFileInfo(String ruta): muestra la siguiente información del fichero en cuestión:
 * Nombre del fichero
 * Ruta absoluta
 * Fecha de última modificación
 * Tamaño del fichero en KB.
 * Desarrolla una clase llamada ejercicio119 que contendrá el main que permita probar el sistema
 */
public class EjemploFile {
    //Método para crear un archivo nuevo
    public void crearFichero(String ruta) throws IOException {
        File archivo = new File(ruta);

        if (archivo.createNewFile()) {
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            }else {
                System.out.println("El archivo ya existe.");
            }
        }
    }

    //Método para borrar un archivo
    public void borrarFichero(String ruta) {
        File archivo = new File(ruta);
        if (archivo.delete()) {
            System.out.println("El archivo ha sido");
        }else {
            System.out.println("Fallo al intentar borrar el archivo.");
        }
    }

    //Método para copiar el contenido de un archivo a otro
    public void copiar(String origen, String destino) throws IOException {
        File archivoOrigen = new File(origen);
        File archivoDestino = new File(destino);

        //Utilizando Files para copiar
        Files.copy(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Archivo copiado : " + origen + " a " + destino);
    }

    //Método para obtener información del archivo
    public void getFileInfo(String ruta) {
        File archivo = new File(ruta);

        if (archivo.exists()) {
            System.out.println("Nombre del archivo: " + archivo.getName());
            System.out.println("Ruta absolucto: " + archivo.getAbsolutePath());

            //Formaterar la fecha de la última modificación
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            System.out.println("Última modificación " + sdf.format(archivo.lastModified()));

            //Obtener el tamaño en KB
            long tamanoKB = archivo.length() / 1024;
            System.out.println("Tamaño " + tamanoKB + " KB");
        }else {
            System.out.println("El archivo no existe");
        }
    }
}
