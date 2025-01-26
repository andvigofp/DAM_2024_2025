/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import java.util.ArrayList;
import java.util.List;
import objeto.Mueble;


/**
 *
 * @author Andrés Fernández Pereira
 */

public class MuebleControlador {
    private List<Mueble> muebles;

    public MuebleControlador() {
        muebles = new ArrayList<>();
    }

    public void agregarMueble(Mueble mueble) {
        muebles.add(mueble);
    }

    public Mueble obtenerMueble(int indice) {
        if (indice >= 0 && indice < muebles.size()) {
            return muebles.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }

    public void eliminarMueble(int indice) {
        if (indice >= 0 && indice < muebles.size()) {
            muebles.remove(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de rango.");
        }
    }

    public void eliminarTodosLosMuebles() {
        muebles.clear();
    }

    public List<Mueble> getMuebles() {
        return muebles;
    }
}



