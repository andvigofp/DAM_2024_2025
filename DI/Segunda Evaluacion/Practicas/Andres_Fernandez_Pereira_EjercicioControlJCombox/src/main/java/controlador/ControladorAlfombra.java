/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import objeto.Alfombra;



public class ControladorAlfombra {
    private List<Alfombra> alfombras;

    public ControladorAlfombra() {
        alfombras = new ArrayList<>();
    }

    public void agregarAlfombra(Alfombra alfombra) {
        alfombras.add(alfombra);
    }

    public Alfombra obtenerAlfombra(int indice) {
        if (indice >= 0 && indice < alfombras.size()) {
            return alfombras.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }

    public void eliminarAlfombra(int indice) {
        if (indice >= 0 && indice < alfombras.size()) {
            alfombras.remove(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }

    public void eliminarTodas() {
        alfombras.clear();
    }

    public List<Alfombra> getAlfombras() {
        return alfombras;
    }
}

