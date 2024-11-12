package Ej106;

import java.io.File;
import java.io.FilenameFilter;

public class FiltrarNombre implements FilenameFilter {
    private String extension;

    public FiltrarNombre() {
    }

    public FiltrarNombre(String extension) {
        this.extension = extension;
    }


    //Método accept para la interfaz para Filtrar nombre del archivo (FilenameFilter)
    @Override
    public boolean accept(File directorio, String nombre) {
        return nombre.endsWith(this.extension);
    }

    //Método para filtrar archivos por extensión
    public static void filtrar(String ruta, String extension) {
        File carpeta = new File(ruta);
        if (carpeta.isDirectory()) {
            FilenameFilter filtro = new FiltrarNombre(extension);

            String[] archivosFiltrados = carpeta.list(filtro);

            if (archivosFiltrados != null && archivosFiltrados.length > 0) {
                System.out.println("Archivos con la extensión " + extension +  ":");
                for (String archivo : archivosFiltrados) {
                    System.out.println(archivo);
                }
            }else {
                System.out.println("No se encontraron archivos con la extensión " + extension + ".");
            }
        }else {
            System.out.println("La ruta proporcionada no es un directorio.");
        }
    }
}
