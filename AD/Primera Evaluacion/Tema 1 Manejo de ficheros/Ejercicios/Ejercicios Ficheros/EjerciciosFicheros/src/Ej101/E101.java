package Ej101;

import java.io.File;
import java.io.IOException;

/**
 * Desarrolla un programa Java que permita mostrar por pantalla la siguiente información de un fichero o de un directorio:
 *
 * Nombre
 * Ruta relativa
 * Ruta absoluta
 * Si permite lectura
 * Si permite escritura
 * Su tamaño
 * Si es un fichero o no
 */

public class E101 {
    public static void main(String[] args) throws IOException {
        File f = new File("./arhcivo.txt");
        f.createNewFile();


        if (f.exists()) {
            System.out.println("Nombre del Fichero " + f.getName());
            System.out.println("Ruta Relativa " + f.getPath());
            System.out.println("Ruta Abosuluta " + f.getAbsolutePath());
            System.out.println("Lectura " + f.canRead());
            System.out.println("Esctura " + f.canWrite());
            System.out.println("Tamaño Fichero " + f.length());
            System.out.println("Fichero " +  " " + f.isFile());

        }



    }
}
