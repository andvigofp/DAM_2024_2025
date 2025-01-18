package Ej111;

import java.io.File;
import java.io.IOException;

/**
 * Crea una clase Persona, que tenga las siguientes características:
 *
 * Dos atributos nombre y edad.
 * Getters y setters.
 * Constructor con dos parámetros.
 * Que la clase sea serializable.
 * A continuación, crea una clase SerializarPersona, que tenga los siguientes métodos:
 *
 * public void escribirPersonaEnFichero(Persona persona, File fichero): escribe la información de la persona en el fichero.
 * public Persona leerPersonaDeFichero(File fichero): devuelve un objeto Persona con los datos leídos del fichero.
 * NOTA: debes usar las clases ObjectInputStream y ObjectOutputStream.
 *
 * Además, debes implementar un método main que haga lo siguiente:
 *
 * Crear una persona.
 * Crear un fichero persona.txt.
 * Escribir los datos de la persona en el fichero.
 * Leer los datos y almacenarlos en un objeto diferente.
 * Mostrar por consola los datos recuperados.
 */

public class SerializarPersona {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MEJ111 metodos = new MEJ111();
        Persona persona = new Persona("Francisco", 65);

        File file = new File("./src/Ej111/persona.txt");

        metodos.escribirPersonaEnFichero(persona, file);

        Persona persona2 = metodos.leerPersonaDeFichero(file);
        System.out.println("Los datos de la nueva persona son:\nNombre " + persona2.getNombre() + "\nEdadad " + persona2.getEdad());
    }
}
