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
public class Ej103A {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String nombreProducto;
        double precioProducto, totalCompra=0;
        int cantidadProductos=0;
        String continuar="SI";

        while (!continuar.toUpperCase().equals("NO")) {
            //Soliciatr nombre del producto
            System.out.println("Introduce el nombre del produccto");
            nombreProducto = teclado.next();

            //Solicitar precio del producto
            try {
                System.out.println("Introduce el precio de " + nombreProducto);
                precioProducto = teclado.nextDouble();

                //Acumular el total de la compra y la cantidad de producto
                totalCompra += precioProducto;
                cantidadProductos++;

            }catch (Exception e) {
                System.out.println("Error: El precio debe ser un número válido.");
                teclado.nextLine(); //Limpiar el buffer en caso de error
                continue;

            }
            //Preguntar si se desea continuar
            do {
                System.out.println("¿Desea añadir más productos? (SI/NO)");
                continuar = teclado.next();
            }while (!continuar.toUpperCase().equals("SI") && !continuar.toUpperCase().equals("NO"));
        }
        System.out.println("Has comprado " + cantidadProductos + " productos.");
        System.out.println("El total de la compra es: " + totalCompra + " euros.");
    }
}
