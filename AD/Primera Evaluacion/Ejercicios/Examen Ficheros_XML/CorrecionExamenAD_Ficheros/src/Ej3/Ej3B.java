package Ej3;

import java.util.ArrayList;
import java.util.List;

/**
 * Los ficheros paises1.txt, paises2.txt y paises3.txt contienen una lista de 20 países.
 * Debes implementar un programa en Java que fusione los países de los 3 ficheros en un
 * único fichero, ordenados según el número de caracteres de cada país. Es decir, debe leer
 * las palabras de los 3 ficheros y crear un nuevo fichero paises.txt, en el que aparezcan
 * todos los paises ordenados desde el que más caracteres contiene al que menos (Estados
 * Unidos … Perú).
 */

public class Ej3B {
    public static void main(String[] args) {
        MEj3B metodos = new MEj3B();

        String paises1 = "./src/Ej3/Paises/paises1.txt";
        String paises2 = "./src/Ej3/Paises/paises2.txt";
        String paises3 = "./src/Ej3/Paises/paises3.txt";
        String fusionPaises = "./src/Ej3/paisesb.txt";

        List<String> paises = new ArrayList<>();

        // Archivos de entrada
        String[] archivos = {paises1, paises2, paises3};

        // Leer los tres archivos y agregar los países a la lista
        for (String archivo : archivos) {
            metodos.leerPaisesArchivo(archivo, paises);
        }

        // Ordenar la lista de países por la longitud de los nombres (de mayor a menor)
        paises.sort((p1, p2) -> Integer.compare(p2.length(), p1.length()));

        // Escribir la lista de países ordenados en un nuevo archivo
        metodos.escribirPaisesArchivo(fusionPaises, paises);

        System.out.println("Fusión completada. Los países están ordenados en el archivo 'paisesb.txt'.");

    }
}
