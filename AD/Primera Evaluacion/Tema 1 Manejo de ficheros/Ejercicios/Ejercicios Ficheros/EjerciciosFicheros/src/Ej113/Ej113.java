package Ej113;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Crea un directorio llamado directorio que tenga dos archivos: origen.txt y destino.txt.
 *
 * Crea una clase CopiaFichero, que implemente método main. Utiliza las clases Paths y Files de la librería Java NIO,
 * y el método copy(), implementa un programa en Java que permita copiar el contenido del fichero origen.txt en el fichero destino.txt.
 */
public class Ej113 {
    public static void main(String[] args) {
        //Definir las rutas de los archivos origen y destino
        Path origenPatch = Paths.get("./src/Ej113/origen.txt");
        Path destioPatch = Paths.get("./src/Ej113/destino.txt");


        try {
            //Copiar el archivo origen en el archivo destino
            Files.copy(origenPatch, destioPatch, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("El archivo ha sido copiado correctamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al copiar el archivo.");
            e.printStackTrace();
        }
    }
}
