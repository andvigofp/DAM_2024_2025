package Ej105;

import java.io.File;
import java.io.IOException;


public class ManejoFicheros {
    public void crearDirectorio(String directorio) {
        File file = new File(directorio);
        if (!file.exists()) {
            file.mkdir();
            System.out.println("Directorio creado");
        }else {
            System.out.println("El directorio ya existía");
        }
    }

    public void crearFichero(String fichero) throws IOException {
        File file = new File(fichero);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Se ha creado correctamente el fichero");
        }else {
            System.out.println("El fichero está creado");
        }
    }

    public void borrarFichero(String fichero) {
        File file = new File(fichero);

       if (file.exists()) {
           System.out.println("El fichero se a borrado corractemente");
           file.delete();
       }else {
           System.out.println("El fichero no existe");
       }
    }

    public void borrarDirectorio(String directorio) {
        System.out.println("Borrando el directorio");

        File[] files = new File(directorio).listFiles();

        for (int i=0; i<files.length; i++) {
            borrarFichero(String.valueOf(files[i]));
           /**if (!files[i].isDirectory()) {
               files[i].delete();
               System.out.println("Eliminando archivos");
           }**/

        }
        new File(directorio).delete();

    }

    public void  listarDirectrio(String ruta) {
        File file = new File(ruta);

        System.out.println("Listamos el contenido");

        String[] archivos = file.list();

        for (String archivo : archivos) {
            System.out.println(archivo);
        }

    }
}


