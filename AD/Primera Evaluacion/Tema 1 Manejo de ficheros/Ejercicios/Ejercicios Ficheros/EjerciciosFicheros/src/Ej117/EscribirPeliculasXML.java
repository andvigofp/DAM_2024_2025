package Ej117;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * crea una clase llamada EscribirPeliculasXML, que tenga un método main. Dentro del main, utiliza la clase Transformer para generar un nuevo fichero XML, llamado copiaPeliculas.xml, que sea una copia del fichero peliculas.xml, el cual tiene el siguiente contenido:
 *
 * <?xml version="1.0" encoding="UTF-8"?>
 * <peliculas>
 *     <pelicula id="1">
 *         <titulo>El señor de los anillos</titulo>
 *         <ano>1999</ano>
 *         <precio>19.99</precio>
 *     </pelicula>
 *     <pelicula id="2">
 *         <titulo>Star Wars</titulo>
 *         <ano>2005</ano>
 *         <precio>12.50</precio>
 *     </pelicula>
 *     <pelicula id="3">
 *         <titulo>Harry Potter</titulo>
 *         <ano>2001</ano>
 *         <precio>8.25</precio>
 *     </pelicula>
 * </peliculas>
 */
public class EscribirPeliculasXML {
    public static void main(String[] args) {
        try {
            // Cargar el archivo de origen
            File inputFile = new File("./src/Ej117/peliculas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Crear el archivo de destino
            File outputFile = new File("./src/Ej117/copiaPeliculas.xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(outputFile);

            // Escribir el contenido en el nuevo archivo XML
            transformer.transform(source, result);
            System.out.println("El archivo copiaPeliculas.xml ha sido creado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
