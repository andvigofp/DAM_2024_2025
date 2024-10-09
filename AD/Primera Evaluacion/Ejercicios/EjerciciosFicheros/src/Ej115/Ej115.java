package Ej115;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
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
 * Se pide pintar el diagrama que represente su DOM.
 */

public class Ej115 {
    public static void main(String[] args) {

        try {
            //Cargar el archivo XML
            File archivoXML = new File("./src/Ej115/peliculas.xml");

            //Crear la fábrica y el analizador del XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();

            //Obtener el elemento raíz
            Element raiz = doc.getDocumentElement();
            System.out.println("<" + raiz.getNodeName() + ">");

            //Obtener la lista de películas
            NodeList listaPeliculas = doc.getElementsByTagName("pelicula");

            // Recorrer las películas
            for (int i = 0; i < listaPeliculas.getLength(); i++) {
                Node nodoPelicula = listaPeliculas.item(i);

                // Verificar que sea un nodo de tipo Element
                if (nodoPelicula.getNodeType() == Node.ELEMENT_NODE) {
                    Element pelicula = (Element) nodoPelicula;
                    System.out.println("    <" + pelicula.getNodeName() + ">");

                    // Obtener y mostrar los valores de título, año y precio
                    System.out.println("        titulo: " + pelicula.getElementsByTagName("titulo").item(0).getTextContent());
                    System.out.println("        ano: " + pelicula.getElementsByTagName("ano").item(0).getTextContent());
                    System.out.println("        precio: " + pelicula.getElementsByTagName("precio").item(0).getTextContent());
                    System.out.println("    </" + pelicula.getNodeName() + ">");
                }
            }

            // Cerrar la etiqueta de peliculas
            System.out.println("</" + raiz.getNodeName() + ">");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}