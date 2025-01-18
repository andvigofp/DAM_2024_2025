/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objeto;

/**
 *
 * @author PcVIP
 */
public class Alfombra {
    private String modelo;
    private String color;
    private double ancho;
    private double alto;

    public Alfombra() {}

    public Alfombra(String modelo, String color, double ancho, double alto) {
        this.modelo = modelo;
        this.color = color;
        this.ancho = ancho;
        this.alto = alto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        if (ancho > 0) {
            this.ancho = ancho;
        } else {
            throw new IllegalArgumentException("El ancho debe ser mayor que 0.");
        }
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        if (alto > 0) {
            this.alto = alto;
        } else {
            throw new IllegalArgumentException("El alto debe ser mayor que 0.");
        }
    }

    public double calcularArea() {
        return ancho * alto;
    }

    @Override
    public String toString() {
        return "MODELO: " + modelo + " - COLOR: " + color + 
               " - DIMENSIONES: " + ancho + " x " + alto + 
               " - ÁREA: " + calcularArea();
    }
}

