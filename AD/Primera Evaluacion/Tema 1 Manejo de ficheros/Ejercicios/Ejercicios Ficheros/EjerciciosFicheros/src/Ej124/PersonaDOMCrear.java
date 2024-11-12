package Ej124;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PersonaDOMCrear {
    private static final String PERSONAS_ACTIVAS_OUTPUT_FILE = Paths.get("src", "./Ej124/doc", "personas_activas.xml").toString();

    public static void main(String[] args) {
        // Crear personas de ejemplo (rellena con tus datos)
        ArrayList<Persona> personasActivas = new ArrayList<>();
        personasActivas.add(new Persona("1", "Lucas", "12345678A", 23, 25000.33, false));
        personasActivas.add(new Persona("3", "Eva", "12345678C", 54, 40000.33, false));

        // Crear el archivo personas_activas.xml
        crearArchivoPersonasActivas(personasActivas);
    }

    private static void crearArchivoPersonasActivas(ArrayList<Persona> personasActivas) {
        try {
            // Crear el documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Crear el elemento raíz
            Element rootElement = doc.createElement("personas");
            doc.appendChild(rootElement);

            // Agregar cada persona al documento
            for (Persona persona : personasActivas) {
                Element personaElement = doc.createElement("persona");
                personaElement.setAttribute("id", persona.getId()); // Usar id como atributo
                personaElement.setAttribute("borrado", String.valueOf(persona.isBorrado())); // Usar borrado como atributo

                Element nombreElement = doc.createElement("nombre");
                nombreElement.appendChild(doc.createTextNode(persona.getNombre()));
                personaElement.appendChild(nombreElement);

                Element dniElement = doc.createElement("dni");
                dniElement.appendChild(doc.createTextNode(persona.getDni()));
                personaElement.appendChild(dniElement);

                Element edadElement = doc.createElement("edad");
                edadElement.appendChild(doc.createTextNode(String.valueOf(persona.getEdad())));
                personaElement.appendChild(edadElement);

                Element salarioElement = doc.createElement("salario");
                salarioElement.appendChild(doc.createTextNode(String.valueOf(persona.getSalario())));
                personaElement.appendChild(salarioElement);

                rootElement.appendChild(personaElement);
            }

            // Escribir el contenido en el archivo
            File outputFile = new File(PERSONAS_ACTIVAS_OUTPUT_FILE);
            outputFile.getParentFile().mkdirs(); // Crear los directorios si no existen
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter(outputFile));
            transformer.transform(source, result);

            System.out.println("Archivo personas_activas.xml creado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al crear el archivo personas_activas.xml: " + e.getMessage());
        }
    }
}