package Ej106;

import java.io.File;

public class FiltrarTamano {
    //Tamaño mínmo en bytes
    private float tamano;

    public FiltrarTamano() {
    }

    public FiltrarTamano(float tamano) {
        this.tamano = tamano;
    }

    //Método para filtrar archivos por tamano
    public static void filtrar(String direccion, float tamano) {
        File carpeta = new File(direccion);

        if (carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();

            if (archivos !=null && archivos.length > 0) {
                System.out.println("Archivos con tamaño mayor a " + tamano + " bytes ");

                for (File archivo : archivos) {
                    if (archivo.isFile() && archivo.length() > tamano) {
                        System.out.println(archivo.getName() + " - " + archivo.length()  + " bytes ");
                    }
                }
            }else {
                System.out.println("No se encontraron archivos con el tamaño especifico.");
            }
        }else {
            System.out.println("La ruta proporcionada no es un directorio.");
        }
    }
}
