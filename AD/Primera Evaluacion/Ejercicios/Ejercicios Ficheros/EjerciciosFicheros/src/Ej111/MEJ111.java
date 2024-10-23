package Ej111;

import java.io.*;

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
public class MEJ111 {
    public void escribirPersonaEnFichero(Persona persona, File fichero) throws IOException {
        FileOutputStream f = new FileOutputStream(new File("./src/Ej111/persona.txt"));
        ObjectOutputStream o = new ObjectOutputStream(f);

        //Escribimos el objecto
        o.writeObject(persona);

        o.close();
    }

    public  Persona leerPersonaDeFichero(File fichero) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("./src/Ej111/persona.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);

        //Leer Object Persona
        Persona p = (Persona) oi.readObject();
        return p;
    }
}
