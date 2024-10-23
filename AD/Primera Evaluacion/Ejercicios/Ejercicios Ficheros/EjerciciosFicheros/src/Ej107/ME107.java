package Ej107;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ME107 {
    public static void clasificarDirectorio(String ruta) {
        File carpeta = new File(ruta);

        //Verifica si la ruta es un directorio válido
        if (carpeta.isDirectory()) {
            //Para clasificar ficheros por extensión
            Map<String, StringBuilder> archivosExtenion = new HashMap<>();

            //Lista todos los archivos y subdirectorios
            File[] contenido = carpeta.listFiles();

            if (contenido !=null && contenido.length > 0) {
                System.out.println("Contenido del directorio " + ruta);

                for (File archivo : contenido) {
                    if (archivo.isDirectory()) {
                        System.out.println("[Directorio]: " + archivo.getName());
                    }else if (archivo.isFile()) {
                        //Extrae la extensión del archivo
                        String nombreArchivo = archivo.getName();
                        String extension = "";

                        int index = nombreArchivo.lastIndexOf(".");
                        if (index > 0) {
                            extension = nombreArchivo.substring(index).toLowerCase();
                        }else {
                            extension = "[Sín extensió]";
                        }

                        //Agrupa los archivos por su extensión
                        archivosExtenion.putIfAbsent(extension, new StringBuilder());
                        archivosExtenion.get(extension).append(nombreArchivo).append("\n");
                    }
                }

                //Imprime los archivos agraupados por extensión
                System.out.println("\nArchivos clasificados por extensión");
                for (Map.Entry<String, StringBuilder> entry: archivosExtenion.entrySet()) {
                    System.out.println("Extensión " + entry.getKey());
                    System.out.println(entry.getValue().toString());
                }
            }else {
                System.out.println("El directorio está vacío.");
            }
        }else {
            System.out.println("La ruta proporcionado no es un directorio ");
        }
    }
}
