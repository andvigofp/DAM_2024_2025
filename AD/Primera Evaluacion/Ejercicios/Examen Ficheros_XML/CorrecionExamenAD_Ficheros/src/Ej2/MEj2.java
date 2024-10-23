package Ej2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MEj2 {
    public void crearArchivoBinario(File ruta) {
        try {
            // Leer el archivo XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("./src/Ej2/productos.xml"));
            doc.getDocumentElement().normalize();

            // Pedir al usuario el valor de stock
            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduce el valor mínimo de stock:");
            int stockUsuario = teclado.nextInt();

            // Extraer productos del XML
            NodeList productosXML = doc.getElementsByTagName("producto");
            ArrayList<Producto> productos = new ArrayList<>();

            for (int i = 0; i < productosXML.getLength(); i++) {
                Node nodo = productosXML.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    double precio = Double.parseDouble(elemento.getElementsByTagName("precio").item(0).getTextContent());
                    int stock = Integer.parseInt(elemento.getElementsByTagName("stock").item(0).getTextContent());

                    if (stock > stockUsuario) {
                        Producto producto = new Producto(nombre, precio, stock);
                        productos.add(producto);
                    }
                }
            }

            // Guardar los productos en el archivo binario
            try (ObjectOutputStream escribir = new ObjectOutputStream(new FileOutputStream(ruta))) {
                for (Producto producto : productos) {
                    escribir.writeObject(producto);
                }
            }
            System.out.println("Archivo 'producto.bin' creado exitosamente con los productos filtrados.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void mostrarContenidoBinario(File ruta) {
        try (ObjectInputStream leer = new ObjectInputStream(new FileInputStream(ruta))){
            while (true) {
                try {
                    Producto producto = (Producto) leer.readObject();
                    System.out.println(producto);
                }catch (EOFException e) {
                    break; // Se alcanza el final del producto
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
   }
}
