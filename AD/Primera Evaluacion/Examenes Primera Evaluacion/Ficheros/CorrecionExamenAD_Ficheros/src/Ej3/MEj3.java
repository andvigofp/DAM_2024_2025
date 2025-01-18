package Ej3;

import java.io.*;
import java.util.List;

public class MEj3 {
    //Método para leer lo países de un archivo y agregarlos a la lista
    public void leerPaisesArchivo(String ruta, List<String> paises, int maxSize){
        try (BufferedReader leer = new BufferedReader(new BufferedReader(new FileReader(ruta)))){
            String linea;

            while ((linea = leer.readLine())!=null && paises.size() < maxSize) {
                // Añadir el país a la lista, eliminando espacios en blanco
                paises.add(linea.trim());
                System.out.println(linea);
            }

        }catch (Exception e) {
            System.out.println("Error al leer el archivo: " + ruta);
            e.printStackTrace();
        }
    }

    // Método para escribir los páises en un archivo
    public void escribirPaisesArchivo(String ruta, List<String> paises) {
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter(ruta))){
            for (String pàis: paises) {
                escribir.write(pàis);
                escribir.newLine();
            }
        }catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + ruta);
            e.printStackTrace();
        }
    }
}
