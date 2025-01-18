package Ej3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Los ficheros paises1.txt, paises2.txt y paises3.txt contienen una lista de 20 países.
 * Debes implementar un programa en Java que fusione los países de los 3 ficheros en un
 * único fichero, ordenados según el número de caracteres de cada país. Es decir, debe leer
 * las palabras de los 3 ficheros y crear un nuevo fichero paises.txt, en el que aparezcan
 * todos los paises ordenados desde el que más caracteres contiene al que menos (Estados
 * Unidos … Perú).
 */

public class Ej3 {
    public static void main(String[] args) {
        MEj3 metodos = new MEj3();


        String paises1 = "./src/Ej3/Paises/paises1.txt";
        String paises2 = "./src/Ej3/Paises/paises2.txt";
        String paises3 = "./src/Ej3/Paises/paises3.txt";
        String fusionPaises = "./src/Ej3/paises.txt";

        List<String> paises = new ArrayList<>();
        //Tamaño máximo deseado
        int maxSize = 20;


        // Archivos de entrada
        String[] archivos = {paises1, paises2, paises3};

        // Leer los tres archivos y agregar los países a la lista
        for (String ruta : archivos) {
            if (paises.size() <maxSize ) {
                metodos.leerPaisesArchivo(ruta, paises, maxSize);
            }
        }

        // Ordenar la lsita de países por la longitud de los nombres (mayor a menor)
        Collections.sort(paises, (p1, p2) -> Integer.compare(p2.length(), p1.length()));

        // Escribir la lista de páises ordenados en nuevo archivo
        metodos.escribirPaisesArchivo(fusionPaises, paises);

        System.out.println("Fusión completada, Los páises están prdenadas en el archivo 'paises.txt.'");
    }
}
