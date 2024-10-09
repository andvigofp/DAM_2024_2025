package Ej120;

import java.io.*;
import java.util.HashMap;

public class Config {

    //Método para crear un archivo de configuración con un HashMap
    public static void crearConfigFile(HashMap<String, String> mapa, String rutaFichero) throws IOException {
        //Usamos BufferedWriter para escribir un archivo basado en caracteres
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaFichero))) {
            for (HashMap.Entry<String, String> entry : mapa.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();  // Añadir nueva línea
            }
        }
    }

    //Método para leer el valor de una clave en el archivo de configuración
    public static String leerConfig(String rutaFichero, String clave) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Dividimos la línea en clave y valor usando el "=" como separador
                String[] partes = linea.split("=");
                if (partes[0].equals(clave)) {
                    return partes[1];  // Devolver el valor si la clave coincide
                }
            }
        }
        return null;  // Devolver null si no se encuentra la clave
    }
}

