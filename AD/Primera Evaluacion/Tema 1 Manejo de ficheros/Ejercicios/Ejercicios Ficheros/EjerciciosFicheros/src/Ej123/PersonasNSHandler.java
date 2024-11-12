package Ej123;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class PersonasNSHandler extends DefaultHandler {
    private ArrayList<Persona> personasActivas = new ArrayList<>();
    private ArrayList<Persona> personasInactivas = new ArrayList<>();
    private Persona persona;
    private StringBuilder buffer = new StringBuilder();
    private String namespaceURI;

    public PersonasNSHandler(String namespaceURI) {
        this.namespaceURI = namespaceURI;
    }

    public ArrayList<Persona> getPersonasActivas() {
        return personasActivas;
    }

    public ArrayList<Persona> getPersonasInactivas() {
        return personasInactivas;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // Verificar si el URI coincide con el espacio de nombres principal o el activo
        if (namespaceURI.equals(uri) || "http://www.personas.com/active".equals(uri)) {
            if ("persona".equals(localName)) {
                persona = new Persona();
                // Leer el atributo "borrado" si está presente
                String borradoAttr = attributes.getValue("borrado");
                persona.setBorrado(borradoAttr != null && Boolean.parseBoolean(borradoAttr));
                // Leer el atributo "dni" si está presente en los atributos
                persona.setDni(attributes.getValue("dni"));
            }
            buffer.setLength(0); // Limpiar el buffer
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Solo procesar elementos dentro del namespace correcto
        if (namespaceURI.equals(uri) || "http://www.personas.com/active".equals(uri)) {
            switch (localName) {
                case "nombre":
                    persona.setNombre(buffer.toString().trim());
                    break;
                case "dni":
                    persona.setDni(buffer.toString().trim());
                    break;
                case "edad":
                    persona.setEdad(Integer.parseInt(buffer.toString().trim()));
                    break;
                case "salario":
                    persona.setSalario(Double.parseDouble(buffer.toString().trim()));
                    break;
                case "persona":
                    // Clasificar la persona según su estado "borrado"
                    if (persona.isBorrado()) {
                        personasInactivas.add(persona);
                    } else {
                        personasActivas.add(persona);
                    }
                    break;
            }
        }
    }
}