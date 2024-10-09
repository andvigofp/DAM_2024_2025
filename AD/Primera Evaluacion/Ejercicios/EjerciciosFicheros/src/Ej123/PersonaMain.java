package Ej123;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PersonaMain {
    public static void main(String[] args) {
            // Crear el archivo personas.xml si no existe
            //crearArchivoPersonas();

            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();

                PersonasHandler handler = new PersonasHandler();
                saxParser.parse(new File("./src/Ej123/personas.xml"), handler);

                // Mostrar el contenido del ArrayList
                ArrayList<Persona> personas = handler.getPersonas();
                for (Persona persona : personas) {
                    System.out.println(persona);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**private static void crearArchivoPersonas() {
            File archivo = new File("./src/Ej123/personas.xml");
            if (!archivo.exists()) {
                try (FileWriter writer = new FileWriter(archivo)) {
                    writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                    writer.write("<personas>\n");
                    writer.write("    <persona dni=\"12345678A\" borrado=\"false\">\n");
                    writer.write("        <nombre>Juan</nombre>\n");
                    writer.write("        <edad>30</edad>\n");
                    writer.write("    </persona>\n");
                    writer.write("    <persona dni=\"87654321B\">\n");
                    writer.write("        <nombre>Ana</nombre>\n");
                    writer.write("        <edad>25</edad>\n");
                    writer.write("    </persona>\n");
                    writer.write("</personas>\n");
                    System.out.println("Archivo personas.xml creado con éxito.");
                } catch (IOException e) {
                    System.out.println("Error al crear el archivo personas.xml");
                    e.printStackTrace();
                }
            } else {
                System.out.println("El archivo personas.xml ya existe.");
            }
        }**/
    }