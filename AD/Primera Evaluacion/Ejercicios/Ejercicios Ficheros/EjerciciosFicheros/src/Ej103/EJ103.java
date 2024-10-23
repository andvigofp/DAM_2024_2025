package Ej103;
/**
 * Desarrolla un programa Java que permita crea un directorio vacío en la ruta dada. Podéis usar la siguiente
 * ruta si estáis en Windows C:\\Users\\Antonio\\Descargas\\ficheros, si usáis Linux: /home/usuario/Descargas/ficheros
 * o bien podéis usar la ruta que queráis
 */

import java.io.File;
import java.lang.reflect.Field;

public class EJ103 {
    public static void main(String[] args) {

        File d = new File(".\\src\\Ej103\\directorio");
        d.mkdir();
        System.out.println("Se ha creado el directorio correctamente");
    }
}
