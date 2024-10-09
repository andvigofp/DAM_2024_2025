package Ej118;

import Ej108.MEJ108;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Dado el archivo binario alumnos.dat que tiene el siguiente contenido:
 *
 * Apellido "FERNANDEZ","LOPEZ","GOMEZ","CHEN","SERRANO","CASILLAS", "ALONSO"
 * Edad 17, 20, 18, 17, 19, 21, 20
 * Nota 7.5, 4.2, 6.5, 8.0, 3.2, 9.2, 9.9
 *
 * Desarrolla un programa Java que permita:
 *
 * Convertir el archivo binario a un archivo XML llamado alumnos.xml utilizando DOM.
 * Visualizar el archivo alumnos.xml utilizando DOM.
 * Visualizar el archivo alumnos.xml utilizando SAX.
 */
public class Ej118 {
    public static void main(String[] args) throws IOException, SAXException {
        MEj118 metodos = new MEj118();

        metodos.convertidor();
        metodos.visulizarDOM();
        metodos.visualizarSAX();
    }
}
