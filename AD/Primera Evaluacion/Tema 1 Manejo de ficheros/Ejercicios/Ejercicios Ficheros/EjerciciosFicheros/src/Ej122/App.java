package Ej122;



import java.util.ArrayList;

/**
 * Persistencia con RandomAccessFile
 *
 * A partir del ejercicio anterior, modifica la clase RandomAccessPersistencia añadiéndole los siguientes métodos públicos:
 *
 * 1. void escribirPersonas(ArrayList<Persona> personas, String ruta):
 *
 * Debe escribir los datos de todas las personas en el mismo orden que escribirPersona.
 * Si el fichero tiene datos, debe comenzar a escribir desde el final y no sobreescribir todo el fichero.
 * Modifica el método Main de la clase App para probar este método.
 *
 * 2. ArrayList<Persona> leerTodo(String ruta):
 *
 * Debe leer un fichero desde el principio y construir un ArrayList de Persona.
 * Modifica el método Main de la clase App para probar este método.
 *
 * 3.Persona leerPersona(int posicion, String ruta):
 *
 * Debe posicionarse en la persona que ocupa la posición indicada por el parámetro posición y devolver un objeto Persona con los datos correspondientes.
 * Si posicion = 1, el puntero del fichero deberá situarse en el byte 0, si posicion = 2, el puntero debe situarse en el byte= número de bytes que ocupa cada persona, etc.
 * Modifica el método Main de la clase App para probar este método.
 *
 * 4. Persona add(int posicion, String ruta, Persona persona):
 *
 * Debe añadir un objeto Persona en la posición indicada.
 * La posicion =1, indica posicionarse en el byte cero, etc.
 * Si ya existía una persona en esa posición, se sobrescribirá.
 * Modifica el método Main de la clase App para probar este método.
 *
 * 5. float sumarSalario(int posicion, String ruta, float incremento):
 *
 * Debe situarse en la Persona que ocupa la posicion indicada (mismas consideraciones que en los apartados anteriores con posición),
 * leer su salario e incrementarlo en la cantidad incremento.
 * Devuelve el nuevo salario.
 * Modifica el método Main de la clase App para probar este método.
 */

public class App {
    public static void main(String[] args) {
        String ruta = "./src/Ej122/persona.dat";

        RandomAccessPersistencia randomAccess = new RandomAccessPersistencia();

        //Crear varias personas
        Persona persona1 = new Persona(1, "12345678A", 30, 2500.0f);
        Persona persona2 = new Persona(2, "87654321B", 25, 3000.0f);
        Persona persona3 = new Persona(3, "11223344C", 35, 3500.0f);

        //Escribir una lista de personas al archivo
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(persona1);
        personas.add(persona2);
        personas.add(persona3);
        randomAccess.escribirPersonas(personas, ruta);
        System.out.println("Se han escrito varias personas en el archivo.");

        //Leer todas las personas del archivo
        ArrayList<Persona> personasLeidas = randomAccess.leerTodo(ruta);
        System.out.println("Personas leídas del archivo:");
        for (Persona p : personasLeidas) {
            System.out.println(p);
        }

        //Leer una persona específica (posición 2)
        Persona personaLeida = randomAccess.leerPersona(2, ruta);
        System.out.println("Persona leída en la posición 2: " + personaLeida);

        //Sobrescribir una persona en una posición específica (posición 2)
        Persona personaNueva = new Persona(4, "99999999Z", 40, 4000.0f);
        randomAccess.add(2, ruta, personaNueva);
        System.out.println("Persona sobrescrita en la posición 2.");

        //Leer todas las personas nuevamente
        personasLeidas = randomAccess.leerTodo(ruta);
        System.out.println("Personas después de la sobrescritura:");
        for (Persona p : personasLeidas) {
            System.out.println(p);
        }

        //Incrementar salario de una persona
        float nuevoSalario = randomAccess.sumarSalario(2, ruta, 500.0f);
        System.out.println("Nuevo salario de la persona en la posición 2: " + nuevoSalario);

        //Leer todas las personas nuevamente después del incremento salarial
        personasLeidas = randomAccess.leerTodo(ruta);
        System.out.println("Personas después de incrementar el salario:");
        for (Persona p : personasLeidas) {
            System.out.println(p);
        }
    }
}