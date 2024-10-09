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

public class PersonaDOMLeer {
    private static final String PERSONA_TAG = "persona";
    private static final String NOMBRE_TAG = "nombre";
    private static final String DNI_TAG = "dni";
    private static final String EDAD_TAG = "edad";
    private static final String SALARIO_TAG = "salario";
    private static final String BORRADO_TAG = "borrado"; // Cambié a un nuevo atributo
    private static final String PERSONAS_INPUT_FILE = Paths.get("src", "./Ej124/doc", "personas.xml").toString();

    public static void main(String[] args) {
        // Leer el contenido del archivo personas.xml
        ArrayList<Persona> personas = leerArchivoPersonas();

        // Mostrar el contenido del ArrayList
        for (Persona p : personas) {
            System.out.println(p);
        }
    }

    private static ArrayList<Persona> leerArchivoPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            File inputFile = new File(PERSONAS_INPUT_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(PERSONA_TAG);
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    // Obtener id
                    String id = eElement.getAttribute("id");

                    // Obtener el nombre
                    String nombre = eElement.getElementsByTagName(NOMBRE_TAG).item(0).getTextContent();

                    // Obtener DNI
                    String dni = eElement.getElementsByTagName(DNI_TAG).item(0).getTextContent();

                    // Obtener edad
                    int edad = Integer.parseInt(eElement.getElementsByTagName(EDAD_TAG).item(0).getTextContent());

                    // Obtener salario
                    double salario = Double.parseDouble(eElement.getElementsByTagName(SALARIO_TAG).item(0).getTextContent());

                    // Obtener estado de borrado (atributo)
                    boolean borrado = eElement.hasAttribute(BORRADO_TAG) &&
                            Boolean.parseBoolean(eElement.getAttribute(BORRADO_TAG));

                    // Agregar la persona a la lista
                    Persona persona = new Persona(id, nombre, dni, edad, salario, borrado);
                    personas.add(persona);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + e.getMessage());
        }
        return personas;
    }
}