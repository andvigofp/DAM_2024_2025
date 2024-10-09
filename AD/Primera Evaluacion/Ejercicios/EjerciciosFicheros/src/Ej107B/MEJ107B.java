package Ej107B;

import java.io.File;
import java.util.ArrayList;

public class MEJ107B {
    public static void clasificarDirectorio(String ruta) {
        System.out.println("Listamos el contenido de: " + ruta);

        String[] archivos = new File(ruta).list();

        //Almacenamiento de los tipos consultados en ese directorio
        ArrayList<String> consultas = new ArrayList<>();

        //Recorremos los archivos
        for (String archivo : archivos) {


            // Si es un directorio comprobamos dentro que tiene
            if (new File(ruta + "/" + archivo).isDirectory()) {
                System.out.println("Nuevo subdirectorio encontrado");
                clasificarDirectorio(ruta + "/" + archivo);
            }

            //Obtención de la extensión del archivo
            String[] archivoSplit = archivo.split("\\.");
            String extension = "." + archivoSplit[archivoSplit.length -1];

            //Mireamos si se compró esa extensión
            if (!consultas.contains(extension)) {

                //Añadimos
                consultas.add(extension);
                System.out.println("Archivos " + extension);

                //Obteneniendo todos los ficheros para essa extensión --> EJ106
                new FiltrarNombre();
                FiltrarNombre.filtrar(ruta, extension);

            }
        }
    }
}
