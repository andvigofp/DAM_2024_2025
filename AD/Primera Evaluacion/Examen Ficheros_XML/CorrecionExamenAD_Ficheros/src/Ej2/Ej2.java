package Ej2;

import java.io.File;

/**
 *
 Realiza un programa Java que nos permita almacenar la información de un producto en
 un fichero llamado producto.bin. En este fichero se almacenarán objetos de tipo
 Producto. La clase Producto tendrá los atributos: nombre, precio y stock.
 La información de los productos se obtendrá del fichero producto.xml el cual contiene
 información de varios productos. El programa tendrá que ser capaz de leer el fichero XML
 y realizar la siguientes acciones:
 - Si el fichero producto.bin no existe:
 - Se creará.
 - El programa le pedirá un número al usuario por teclado que representará el valor
 del stock.
 - Se buscará en el fichero producto.xml todos los productos con un stock mayor
 al indicado por el usuario y se creará un objeto Producto por cada uno.
 - Se almacenarán los objetos en el fichero.
 - Si el fichero producto.bin ya existe, solo se mostrará la información del fichero por
 consola.
 NOTA: En el caso de no saber trabajar con XML, se sustituirá toda la lógica de la gestión
 del fichero XML por un bucle que permita introducir los valores de los atributos del objeto
 Producto por terminal.
 Para realizar el ejercicio, puedes utilizar las siguientes clases:

 - DocumentBuilder.
 - DocumentBuilderFactory.
 - Document.
 */

public class Ej2 {
    public static void main(String[] args) {
        MEj2 metodos = new MEj2();

        File ruta = new File("./src/Ej2/producto.bin");

        if (!ruta.exists()) {
            //Si el archivo binario no existe
            metodos.crearArchivoBinario(ruta);
            System.out.println("Archivo correctamente");
        }else {
            // Si el archivo binario ya existe
            metodos.mostrarContenidoBinario(ruta);
        }
    }
}
