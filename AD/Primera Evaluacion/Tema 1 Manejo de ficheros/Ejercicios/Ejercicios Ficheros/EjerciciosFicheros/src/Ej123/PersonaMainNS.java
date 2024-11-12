package Ej123;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PersonaMainNS {
    public static void main(String[] args) {
        // Crear el archivo personas_ns.xml
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            SAXParser saxParser = factory.newSAXParser();

            PersonasNSHandler handler = new PersonasNSHandler("http://www.personas.com");
            saxParser.parse(new File("./src/Ej123/personas_ns.xml"), handler);

            // Mostrar personas activas
            System.out.println("Personas activas:");
            for (Persona persona : handler.getPersonasActivas()) {
                System.out.println(persona);
            }

            // Mostrar personas inactivas
            System.out.println("Personas inactivas:");
            for (Persona persona : handler.getPersonasInactivas()) {
                System.out.println(persona);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
