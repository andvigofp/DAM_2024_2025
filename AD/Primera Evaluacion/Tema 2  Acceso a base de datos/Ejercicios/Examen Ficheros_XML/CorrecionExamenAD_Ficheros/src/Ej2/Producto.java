package Ej2;

import java.io.Serializable;

public class Producto implements Serializable {
    private String nombre;
    private double preciop;
    private int stock;

    public Producto(String nombre, double preciop, int stock) {
        this.nombre = nombre;
        this.preciop = preciop;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPreciop() {
        return preciop;
    }

    public void setPreciop(double preciop) {
        this.preciop = preciop;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", preciop=" + preciop +
                ", stock=" + stock +
                '}';
    }
}
