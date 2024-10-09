package Ej116;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Crea una clase llamada LeerPeliculasXML, que tenga un método main. Dentro del main, utiliza la clase DocumentBuilder para leer el fichero peliculas.xml. El fichero peliculas.xml tendrá el siguiente contenido:
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
 *
 * El método main deberá mostrar la información del fichero XML, con el siguiente formato:
 *
 * Se va a mostrar la información de 3 películas.
 *
 * PELÍCULA #1:
 * Título: El señor de los anillos.
 * Año: 1999.
 * Precio: 19.99.
 *
 * PELÍCULA #2:
 * ...
 */

public class LeerPeliculasXML {
    public static void main(String[] args) {
        try {
            //Crear el archivo de entrada
            File origenFile = new File("./src/Ej116/peliculas.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            //Parsear el archivo XML
            Document doc = dBuilder.parse(origenFile);
            doc.getDocumentElement().normalize();

            //Obtener la lista de peliculas
            NodeList nList = doc.getElementsByTagName("pelicula");
            System.out.println("Se va a mostrar la información de " + nList.getLength() + " películas.\n");

            //Iterar sobre la lista películas y mostrar por la información
            for (int i=0; i< nList.getLength(); i++) {
                Element element = (Element) nList.item(i);
                System.out.println("PELÍCULA = " + (1 + i) + ":");
                System.out.println("Título: " + element.getElementsByTagName("titulo").item(0).getTextContent() + ".");
                System.out.println("Año: " + element.getElementsByTagName("ano").item(0).getTextContent() + ".");
                System.out.println("Precio: " + element.getElementsByTagName("precio").item(0).getTextContent() + ".\n");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
