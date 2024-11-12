package Ej124;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PersonaDOMLeerNS {
    private static final String PERSONA_TAG = "persona";
    private static final String NOMBRE_TAG = "nombre";
    private static final String DNI_TAG = "dni";
    private static final String EDAD_TAG = "edad";
    private static final String SALARIO_TAG = "salario";
    private static final String BORRADO_TAG = "borrado";
    private static final String PERSONAS_NS_URI = "http://www.personas.com"; // Actualiza a la URI correcta
    private static final String PERSONAS_INPUT_FILE = Paths.get("src", "./Ej124/doc", "personas_ns.xml").toString();

    public static void main(String[] args) {

        // Leer el contenido del archivo personas_ns.xml
        ArrayList<Persona> personasActivas = new ArrayList<>();
        ArrayList<Persona> personasInactivas = new ArrayList<>();
        leerArchivoPersonasNS(personasActivas, personasInactivas);

        // Mostrar el contenido de los dos ArrayList
        System.out.println("Personas activas:");
        for (Persona p : personasActivas) {
            System.out.println(p);
        }

        System.out.println("\nPersonas inactivas:");
        for (Persona p : personasInactivas) {
            System.out.println(p);
        }
    }



    private static void leerArchivoPersonasNS(ArrayList<Persona> personasActivas, ArrayList<Persona> personasInactivas) {
        try {
            File inputFile = new File(PERSONAS_INPUT_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagNameNS(PERSONAS_NS_URI, PERSONA_TAG);
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    // Obtener el id
                    String id = eElement.getAttribute("id");

                    // Obtener el nombre
                    String nombre = eElement.getElementsByTagNameNS(PERSONAS_NS_URI, NOMBRE_TAG).item(0).getTextContent();

                    // Obtener el DNI
                    String dni = eElement.getElementsByTagNameNS(PERSONAS_NS_URI, DNI_TAG).item(0).getTextContent();

                    // Obtener la edad
                    int edad = Integer.parseInt(eElement.getElementsByTagNameNS(PERSONAS_NS_URI, EDAD_TAG).item(0).getTextContent());

                    // Obtener el salario
                    double salario = Double.parseDouble(eElement.getElementsByTagNameNS(PERSONAS_NS_URI, SALARIO_TAG).item(0).getTextContent());

                    // Obtener el atributo borrado
                    boolean borrado = eElement.hasAttribute(BORRADO_TAG) &&
                            Boolean.parseBoolean(eElement.getAttribute(BORRADO_TAG));

                    // Crear la persona y agregarla a la lista correspondiente
                    Persona persona = new Persona(id, nombre, dni, edad, salario, borrado);
                    if (!borrado) {
                        personasActivas.add(persona);
                    } else {
                        personasInactivas.add(persona);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + e.getMessage());
        }
    }
}