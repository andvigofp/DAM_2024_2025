package Ej118B;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LeerXMLDOM {
    public static void main(String[] args) {
        try {
            //Cargar el archivo XML
            File inputFile = new File("./src/Ej118B/alumnos.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            //Obtener el elemento raíz
            System.out.println("Elemento raíz: " + doc.getDocumentElement().getNodeName());

            //Obtener la lista de alumnos
            NodeList nList = doc.getElementsByTagName("alumno");

            //Recorrer los elementos de alumno
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);

                //Verificar que sea un nodo de tipo elemento
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Apellido: " + element.getElementsByTagName("apellido").item(0).getTextContent());
                    System.out.println("Edad: " + element.getElementsByTagName("edad").item(0).getTextContent());
                    System.out.println("Nota: " + element.getElementsByTagName("nota").item(0).getTextContent());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


