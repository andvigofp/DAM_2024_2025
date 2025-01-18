package Ej103;

import java.util.Scanner;

/**
 * Realiza un programa en Java que gestione la compra de los productos de un supermercado.
 *
 * El programa solicitará el nombre del producto y su precio en bucle.
 *
 * Después de de introducir estos datos, preguntará si deseamos continuar introduciendo más productos. Las posibles respuestas serán SI para seguir en el bucle y NO para salir.
 *
 * Al terminar, mostrará el importe total de la compra y cuántos productos hemos comprado.
 */

public class EJ103B {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        SuperMercado supermercado = new SuperMercado();

        String nombreProducto;
        double precioProducto;
        String continuar = "SI";

        //Bucle para introducir productos
        while (!continuar.toUpperCase().equals("NO")) {
            //Solicitar nombre del producto
            System.out.println("Introduce el nombre del producto:");
            nombreProducto= teclado.next();

            //Solictar precdio del producto
            try {
                System.out.println("Introduce el precio de " + nombreProducto + ":");
                precioProducto = teclado.nextDouble();

                //Añadir producto al supermercado
                supermercado.agregarProducto(nombreProducto, precioProducto);

            }catch (Exception e) {
                System.out.println("Error: El precio debe ser un número válido.");
                teclado.nextLine(); //Limpiar el buffer en caso de error
                continue;
            }
                //Preguntar si desea continuar
            do {
                System.out.println("¿Desea añadir más productos? (SI/NO)");
                continuar = teclado.next();
            }while (!continuar.toUpperCase().equals("SI") && !continuar.toUpperCase().equals("NO"));
        }

        //Mostrar el resumen final de la compra
        supermercado.mostrarResumen();
    }
}
