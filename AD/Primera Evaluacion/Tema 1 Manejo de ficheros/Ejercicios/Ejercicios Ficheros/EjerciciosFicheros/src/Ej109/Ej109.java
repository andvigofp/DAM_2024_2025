package Ej109;

import java.io.File;
import java.io.IOException;

/**
 * Crea una clase FicheroBinario que represente a un fichero binario. La clase tendrá un atributo de tipo File, que almacenará el fichero correspondiente. Además, tendrá los siguientes métodos:
 *
 * Constructor.
 * Getter.
 * public void escribir(String texto): escribe en el propio fichero el texto pasado por parámetro.
 * public void leer(): muestra por consola el contenido del fichero.
 * public void copiar(FicheroBinario ficheroDestino): copia el contenido del fichero en el fichero de destino.
 * NOTA: Debes usar las clases FileInputStream y FileOutputStream.
 *
 * A continuación, se pide crear una clase ManejoFicherosBinarios, que implemente el método main. En esta clase debes crear dos ficheros binarios origen.bin y destino.bin y hacer lo siguiente:
 *
 * Escribir en el fichero de origen el texto: ESTE ES EL TEXTO DE ORIGEN.
 * Leer y mostrar el contenido del fichero por consola.
 * Copia el contenido al fichero de destino.
 */
public class Ej109 {
    public static void main(String[] args) throws IOException {
        FicheroBinario origenBinario = new FicheroBinario(new File("./src/Ej109/origen.bin"));

        origenBinario.escrictura("ESTE ES EL TEXTO DE ORIGEN");
        origenBinario.lectura();

        FicheroBinario destinoBinario = new FicheroBinario(new File("./src/Ej109/destino.bin"));
        origenBinario.copiar(destinoBinario);
        destinoBinario.lectura();

    }
}
