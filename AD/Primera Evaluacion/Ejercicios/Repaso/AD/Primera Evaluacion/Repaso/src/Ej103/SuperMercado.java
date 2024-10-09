package Ej103;

import java.util.ArrayList;

public class SuperMercado {
    private ArrayList<String> nombreProducto;
    private ArrayList<Double> preciosProducto;

    //Constructor
    public SuperMercado() {
       nombreProducto = new ArrayList<>();
       preciosProducto = new ArrayList<>();
    }

    //Método para agregar un producto
    public void agregarProducto(String nombre, double precio) {
        nombreProducto.add(nombre);
        preciosProducto.add(precio);
    }

    //Método para mostrar el resumen de la compra
    public void mostrarResumen() {
        double totalCompra=0;
        System.out.println("\nResumen de la compra:");

        for (int i=0; i<nombreProducto.size(); i++) {
            System.out.println(i+1 + ". " + nombreProducto.get(i) + " - " + preciosProducto.get(i) + " Euros");
            totalCompra +=preciosProducto.get(i);
        }

        //Mostrar cantidad del productos y total de la compra
        System.out.println("\nHas comprado " + nombreProducto + " productos.");
        System.out.println("El total de la compra es: " + totalCompra + " Euros.");
    }

    //Método para obtener la cantidad de productos
    public int getcantidadProductos() {
        return nombreProducto.size();
    }
}
