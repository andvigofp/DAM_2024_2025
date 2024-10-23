package Ej118B;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class LeerXMLSAX {
    public static void main(String[] args) {
        try {
            //Crear el parser SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            //Definir el manejador de eventos para el parser
            DefaultHandler handler = new DefaultHandler() {
                boolean bApellido = false;
                boolean bEdad = false;
                boolean bNota = false;

                //Cuando se encuentra una etiqueta de apertura
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("apellido")) {
                        bApellido = true;
                    } else if (qName.equalsIgnoreCase("edad")) {
                        bEdad = true;
                    } else if (qName.equalsIgnoreCase("nota")) {
                        bNota = true;
                    }
                }

                //Cuando se encuentra contenido de texto
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (bApellido) {
                        System.out.println("Apellido: " + new String(ch, start, length));
                        bApellido = false;
                    } else if (bEdad) {
                        System.out.println("Edad: " + new String(ch, start, length));
                        bEdad = false;
                    } else if (bNota) {
                        System.out.println("Nota: " + new String(ch, start, length));
                        bNota = false;
                    }
                }
            };

            // Procesar el archivo XML con SAX
            saxParser.parse(new File("./src/Ej118B/alumnos.xml"), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}