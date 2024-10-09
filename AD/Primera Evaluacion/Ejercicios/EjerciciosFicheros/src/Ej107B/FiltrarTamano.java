package Ej107B;

import java.io.File;
import java.io.FilenameFilter;

public class FiltrarTamano implements FilenameFilter {
    //Tamaño mínmo en bytes
    private float tamano;

    public FiltrarTamano() {
    }

    public FiltrarTamano(float tamano) {
        this.tamano = tamano;
    }

    //Método accept para la interfaz para Filtrar por extension  (FilenameFilter)
    @Override
    public boolean accept(File directorio, String nombre) {
        return new  File(directorio, nombre).length() >=tamano;
    }
    //Método para filtrar archivos por tamano
    public static void filtrar(String direccion, float tamano) {
        File carpeta = new File(direccion);

        if (!carpeta.isDirectory()) {
            System.out.println("La ruta debe ser un directorio");
            return;
        }

        File[] files = carpeta.listFiles(new FiltrarTamano(tamano));

        for (File f: files) {
            System.out.println(f.length());
        }
    }
}
