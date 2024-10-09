package Ej118;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MEj118 {
    public void convertidor() throws IOException {
        File fichero = new File("./src/Ej118/alumnos.dat");
        RandomAccessFile file = new RandomAccessFile(fichero, "r");

        //Nos posiciona al principio del fichero aleatorio
        int  id, edad, pos=0;
        Double nota;
        char apellido[] = new char[10], aux;

        //Creamos la factoria
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = (Document) implementation.createDocument(null, "Alumnos", null);

            // Versión de XML con la que vamos a trabajar
            document.setXmlVersion("1.0");
            for(;;) {
                //Fijamos la primera posición
                file.seek(pos);

                //Se obtiene el identificador del alumno
                id=file.readInt();
                for (int i = 0; i < apellido.length; i++) {

                    //Se recorren los caracteres del apellido
                    aux = file.readChar();

                    //Los caracteres del apellido se guardan en un vector
                    apellido[i] = aux;
                }

                //El vector se convierte en cadena
                String apellidoS= new String(apellido);

                //Se obetiene la edad
                edad=file.readInt();

                //Seobtiene la nota media del alumno
                nota=file.readDouble();

                //Id validados desde la posición primera
                if(id>0) {
                    //nodo empleado
                    Element raiz = document.createElement("alumno");
                    document.getDocumentElement().appendChild(raiz);

                    //Se incorpora el ID
                    CrearElemento("id",Integer.toString(id), raiz, document);

                    //Se incorpora el Apellido
                    CrearElemento("apellido",apellidoS.trim(), raiz, document);

                    //Se incorpora la edad
                    CrearElemento("edad",Integer.toString(edad), raiz, document);

                    //Se incorpora la nota
                    CrearElemento("nota",Double.toString(nota), raiz, document);
                }

                //Nos posicionamos en la siguiente posición del siguiente alumno tal y como se explicó
                pos += 36;

                if (file.getFilePointer() == file.length())
                    break;
            }

            //Creación del source, result y transformer para almacenar el contenido
            Source source = new DOMSource(document);

            //Nombre del fichero XML
            Result result = new StreamResult(new java.io.File("./src/Ej118/alumnos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            Result console= new StreamResult(System.out);
            transformer.transform(source, console);
        }catch(ParserConfigurationException | DOMException | IOException | TransformerException e){
            System.err.println("Error: "+e);
        }

        //Cierre del fichero aleatorio .dat
        file.close();
    }

    public  void visulizarDOM() {
        //Creación de la factoria
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();


        try {
            DocumentBuilder dbBuidler = dbFactory.newDocumentBuilder();
            Document document = dbBuidler.parse(new File("./src/Ej118/alumnos.xml"));
            document.getDocumentElement().normalize();

            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());

            //Crea una lista con todos los nodos alumno
            NodeList alumnos = document.getElementsByTagName("alumno");

            //Recorrer la lista
            for (int i=0; i<alumnos.getLength(); i++) {
                Node  alumm = alumnos.item(i);

                //tipo de nodo
                if (alumm.getNodeType()==Node.ELEMENT_NODE) {

                    //Obtener los elementos del nodo
                    Element elemento = (Element)alumm;
                    System.out.println("\nID: "+ getNodo("id",elemento));
                    System.out.println("Apellido: "+ getNodo("apellido",elemento));
                    System.out.println("Edad: "+ getNodo("edad",elemento));
                    System.out.println("Nota: "+ getNodo("nota",elemento));
                    System.out.println();
                }
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void visualizarSAX() throws IOException, SAXException {
        XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
        GestionContenido gestor= new GestionContenido();
        procesadorXML.setContentHandler(gestor);
        InputSource fileXML = new InputSource("./src/Ej118/alumnos.xml");
        procesadorXML.parse(fileXML);
    }


    //Inserción de los datos del alumno que se invoca desde el programa principal (main)
    public void CrearElemento(String datoAlumno, String valor, Element raiz, Document document) {
        //Creamos el nodo hijo
        Element element = document.createElement(datoAlumno);

        //Se le da el valor
        Text text = document.createTextNode(valor);

        //Pegamos el elemneto hijo a la raiz
        raiz.appendChild(element);

        //Pegamos el valor del nodo
        element.appendChild(text);
    }

    //Devuelve Todo los elementods de los nodos
    public static String getNodo(String etiqueta, Element element) {
        NodeList nodoList = element.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valorNodo = (Node) nodoList.item(0);

        //Devuelve el valor del nodo
        return valorNodo.getNodeValue();
    }
}
