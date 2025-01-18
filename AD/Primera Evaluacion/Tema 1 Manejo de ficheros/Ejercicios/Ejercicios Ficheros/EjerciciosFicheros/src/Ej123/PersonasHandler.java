package Ej123;

import Ej123.Persona;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class PersonasHandler extends DefaultHandler {
    private ArrayList<Persona> personas = new ArrayList<>();
    private Persona persona;
    private StringBuilder buffer = new StringBuilder();

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("persona")) {
            persona = new Persona();
            String borradoValue = attributes.getValue("borrado");
            persona.setBorrado(borradoValue != null && Boolean.parseBoolean(borradoValue));
        }
        buffer.setLength(0); // Limpia el buffer
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "persona":
                personas.add(persona); // Agregar persona al ArrayList
                break;
            case "dni":
                persona.setDni(buffer.toString());
                break;
            case "nombre":
                persona.setNombre(buffer.toString());
                break;
            case "edad":
                persona.setEdad(Integer.parseInt(buffer.toString()));
                break;
            case "salario":
                persona.setSalario(Double.parseDouble(buffer.toString()));
                break;
        }
    }
}