package Ej112;

/**
 * Desarrolla un programa Java que permita:
 *
 * Escribir en un fichero binario alumnos.dat de forma aleatoria la siguiente información:
 *
 * Apellido "FERNANDEZ","LOPEZ","GOMEZ","CHEN","SERRANO","CASILLAS", "ALONSO"
 * Edad 17, 20, 18, 17, 19, 21, 20
 * Nota 7.5, 4.2, 6.5, 8.0, 3.2, 9.2, 9.9
 *
 * Visualizar el contenido del fichero anterior de forma aleatoria.
 */
public class Ej112 {
    public static void main(String[] args) {
        Alumno alumno = new Alumno();

        String nombreFichero = "./src/Ej112/alumnos.dat";

        //Escribir la información de los alumnos de forma aleatoria
        alumno.escribirBinarioAleatorio(nombreFichero);
        alumno.leerFicheroBinarioAleatorio(nombreFichero);
    }
}
