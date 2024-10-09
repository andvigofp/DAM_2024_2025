package Ej110;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Crea una clase FicheroTexto, que represente a un fichero de texto.
 *
 * La clase tendrá un atributo de tipo File, que almacenará el fichero correspondiente. Además, tendrá los siguientes métodos:
 *
 * Constructor.
 * Getter.
 * public void escribir(String texto): escribe en el propio fichero el texto pasado por parámetro.
 * public void leer(): muestra por consola el contenido del fichero.
 * NOTA: Debes usar las clases FileReader y FileWriter.
 *
 * A continuación, crea una clase ManejoFicheroTexto, que:
 *
 * Implemente el método main
 * Cree un fichero de texto destino.txt
 * Implemente un menú que muestre las siguientes opciones:
 * 1.- Escribir en el fichero.
 * 2.- Leer fichero.
 * 3.- Salir.
 *
 * En caso de seleccionar la opción 1, el usuario introducirá por consola el texto que quiere escribir en el fichero. El menú debe mostrarse hasta que el usuario seleccione la opción de salir.
 */
public class EJ110 {
    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);

        File fichero = new File("./src/Ej110/destino.txt");

        FicheroTexto texto = new FicheroTexto(fichero);

        int opcion=0;

        while (opcion !=3) {
            System.out.println("1. Esribir el Fichero");
            System.out.println("2. Leer fichero");
            System.out.println("3. Salir");

            opcion = Integer.parseInt(teclado.nextLine());

            if (opcion ==1) {
                System.out.println("Intrdouzca el texto a escribir");
                String textoString = teclado.nextLine();
                texto.escribir(textoString);
            }else if (opcion==2) {
                texto.leer();
            }else if (opcion==3) {
                System.out.println("Fin del programa");
                break;
            }
        }


    }
}
