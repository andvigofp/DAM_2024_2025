package Ej106B;

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

        if (!carpeta.isDirectory()) {
            System.out.println("La ruta debe ser un directorio");
            return;
        }

        File[] files = carpeta.listFiles(new FiltrarNombre(extension));

        for (File f: files) {
            System.out.println(f.getName());
        }
    }
}
