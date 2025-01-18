package Ej121;

/**
 * Vamos a crear programáticamente un fichero de configuración para una aplicación de Personas. Para ello crearemos los siguientes elementos:
 *
 * Paquete teis:
 *
 * Clase App:
 * Método main: permite probar la ejecución del programa
 * Clase Piscina:
 * Con los siguientes atributos:
 * Un identificador numérico de tipo long.
 * Un dni con 8 números y un carácter [A-Z]
 * La edad
 * El salario (como float)
 * Constructor, getter y setters
 * Paquete persistencia
 *
 * Interfaz Persistencia con los métodos:
 * void escribirPersona(Persona persona, String ruta): Escribe los atributos de Persona, de uno en uno, en un fichero establecido por ruta.
 * Persona leerDatos(String ruta): Lee del fichero ruta, los atributos de Persona, de uno en uno, en el mismo orden que se han escrito. Con ellos, crea un objeto de tipo Persona.
 * Clase DataIOPersistencia que implemente la interfaz Persistencia con DataInputStream y DataOutputStream.
 * Clase RandomAccessPersistencia que implemente la interfaz Persistencia con RandomAccessFile.
 * En el main de la clase App primero se probará el funcionamiento usando la clase DataIOPersistencia y después con la clase RandomAccessPersistencia.
 */

public class App {
    public static void main(String[] args) {
        String ruta = "./src/Ej121/persona.dat";

        // Usar DataIOPersistencia
        DataIOPersistencia dataIO = new DataIOPersistencia();
        Persona persona1 = new Persona(1, "12345678A", 30, 2500.0f);
        dataIO.escribirPersona(persona1, ruta);

        Persona leida1 = dataIO.leerDatos(ruta);
        System.out.println("Datos leídos con DataIOPersistencia: " + leida1);

        // Usar RandomAccessPersistencia
        RandomAccessPersistencia randomAccess = new RandomAccessPersistencia();
        Persona persona2 = new Persona(2, "87654321B", 25, 3000.0f);
        randomAccess.escribirPersona(persona2, ruta);

        Persona leida2 = randomAccess.leerDatos(ruta);
        System.out.println("Datos leídos con RandomAccessPersistencia: " + leida2);
    }
}
