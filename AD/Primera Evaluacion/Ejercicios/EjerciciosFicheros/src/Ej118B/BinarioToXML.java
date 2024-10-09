package Ej118B;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class BinarioToXML {
    public static void main(String[] args) {
        try {
            //Leer el archivo binario alumnos.dat
            DataInputStream dataInput = new DataInputStream(new FileInputStream("./src/Ej118B/alumnos.dat"));

            //Crear el documento DOM para el XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            //Crear el elemento raíz <alumnos>
            Element rootElement = doc.createElement("alumnos");
            doc.appendChild(rootElement);

            //Leer los datos del archivo binario
            char[] apellidoBuffer = new char[10]; //Buffer para apellido
            int id, edad;
            double nota;

            while (dataInput.available() > 0) {
                //Leer ID
                id = dataInput.readInt();

                //Leer apellido (10 caracteres)
                for (int i = 0; i < 10; i++) {
                    apellidoBuffer[i] = dataInput.readChar();
                }
                String apellido = new String(apellidoBuffer).trim();

                //Leer edad
                edad = dataInput.readInt();

                //Leer nota
                nota = dataInput.readDouble();

                //Crear el elemento <alumno>
                Element alumno = doc.createElement("alumno");
                rootElement.appendChild(alumno);

                //Crear y agregar los elementos <id>, <apellido>, <edad> y <nota>
                Element idElement = doc.createElement("id");
                idElement.appendChild(doc.createTextNode(Integer.toString(id)));
                alumno.appendChild(idElement);

                Element apellidoElement = doc.createElement("apellido");
                apellidoElement.appendChild(doc.createTextNode(apellido));
                alumno.appendChild(apellidoElement);

                Element edadElement = doc.createElement("edad");
                edadElement.appendChild(doc.createTextNode(Integer.toString(edad)));
                alumno.appendChild(edadElement);

                Element notaElement = doc.createElement("nota");
                notaElement.appendChild(doc.createTextNode(Double.toString(nota)));
                alumno.appendChild(notaElement);
            }

            dataInput.close();

            //Guardar el contenido DOM en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./src/Ej118B/alumnos.xml"));
            transformer.transform(source, result);

            System.out.println("El archivo alumnos.xml ha sido creado con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}