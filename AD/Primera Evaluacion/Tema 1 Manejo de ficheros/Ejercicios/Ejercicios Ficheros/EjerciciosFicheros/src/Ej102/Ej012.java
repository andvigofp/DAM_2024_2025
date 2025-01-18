package Ej102;

import java.io.File;
import java.io.IOException;

/**
 * Desarrolla un programa Java que permita enumerar todos los ficheros y subdirectorios que tiene un directorio dado.
 */
public class Ej012 {
    public static void main(String[] args) throws IOException {
        File f = new File("./");

        String[] listado = f.list();

        for (int i=0; i<listado.length; i++) {
            System.out.println(listado[i]);
        }
    }
}
