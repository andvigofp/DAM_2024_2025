package Ej120;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Flujos de bytes y caracteres
 * Vamos a crear programáticamente un fichero de configuración para una aplicación de Piscinas. Para ello crearemos los siguientes elementos:
 *
 * Paquete config con la clase Config:
 *
 * Crea un método crearConfigFile(HashMap<String, String> mapa, String rutaFichero) que:
 * Reciba un mapa de cadenas de texto en formato clave-valor
 * Cree un fichero basado en caracteres en la rutaFichero
 * Por cada par clave-valor, debe añadir un nueva línea en el fichero con el formato clave=valor
 * Crea un método leerConfig(String rutaFichero, String clave) que:
 * lea el fichero de configuración piscina.config y encuentre el valor de la clave dada por parámetro.
 * Si la encuentra devuelve el valor.
 * Si no, devuelve null.
 * Paquete piscina:
 *
 * Clase App:
 * Método main:
 *
 * Averigua si existe el fichero piscina.config (define una constante).
 * Si no existe piscina.config, crea un HashMap con los siguientes pares clave valor y crea el fichero con el siguiente contenido en el directorio actual del proyecto
 * Su contenido deberá ser:
 * start=true
 *
 * persistencia=true
 *
 * max_franjas=4
 *
 *
 * Clase Piscina:
 * Atributos: largo y ancho del vaso, largo y ancho de la parcela (4 atributos)
 * Tendrá un constructor con todos los atributos.
 * Método int calcularSuperficie(int longitud, int anchura)
 * Paquete persistencia con la clase Persistencia:
 *
 * Crea un método write(Piscina object, String ruta) que sea capaz de guardar un objeto de tipo piscina en un archivo con la ruta especificada.
 * Crea un método Piscina read(String ruta) que:
 * sea capaz de leer los datos del fichero con la ruta especificada y obtener un objeto de tipo Piscina.
 * Si lo consigue, deberá mostrar los datos de la Piscina (Ayúdate del método toString())
 * Implementa dos métodos más para leer y escribir de un fichero una lista de elementos del tipo Piscina.
 */
public class App {
    public static final String CONFIG_FILE = "./src/Ej120/piscina.config";
    public static final String PISCINA_FILE = "./src/Ej120/piscina.dat";
    public static final String PISCINA_LIST_FILE = "./src/Ej120/piscinasList.dat";

    public static void main(String[] args) {
        File configFile = new File(CONFIG_FILE);

        if (!configFile.exists()) {
            HashMap<String, String> config = new HashMap<>();
            config.put("start", "true");
            config.put("persistencia", "true");
            config.put("max_franjas", "4");

            try {
                Config.crearConfigFile(config, CONFIG_FILE);
                System.out.println("Archivo de configuración creado.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Piscina piscina = new Piscina(10, "5", 15, 10);
            Persintencia.write(piscina, PISCINA_FILE);

            Piscina leida = Persintencia.read(PISCINA_FILE);
            System.out.println("Piscina leída: " + leida);

            List<Piscina> piscinaList = new ArrayList<>();
            piscinaList.add(new Piscina(8, "36147672J", 12, 8));
            piscinaList.add(new Piscina(15, "38134567H", 20, 10));

            Persintencia.writeList(piscinaList, PISCINA_LIST_FILE);

            List<Piscina> piscinasLeidas = Persintencia.readList(PISCINA_LIST_FILE);
            System.out.println("Lista de piscinas leídas:");
            for (Piscina p : piscinasLeidas) {
                System.out.println(p);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
