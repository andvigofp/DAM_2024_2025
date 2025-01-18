package Ej114;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Crea un subdirectorio llamado subdirectorio que esté vacío.
 *
 * A continuación, desarrolla un programa en Java que utilizando la librería Java NIO y el método move() permita mover el
 * fichero destino.txt al subdirectorio, cambiándole el nombre al archivo: nuevoDestino.txt.
 */
public class Ej114 {
    public static void main(String[] args) {
        // Definir las rutas de los archivos origen (destino.txt) y destino (nuevoDestino.txt en subdirectorio)
        Path origenPatch = Paths.get("./src/Ej113/destino.txt");
        Path destinoPatch = Paths.get("./src/Ej114/subdirectorio/nuevoDestino.txt");

        try {
            //Mover el archivo destino.txt al subdirectorio y cambiarle el nombre a nuevoDestino.txt
            Files.move(origenPatch, destinoPatch, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("El archivo ha sido movido exitosamente a subdirectorio y renombrado a nuevoDestino.txt.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al mover el archivo.");
            e.printStackTrace();
        }
    }
}
