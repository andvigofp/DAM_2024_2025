/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import modelo.Traballador;

/**
 *
 * @author Andrés Fernández Pereira
 */

public class ModeloDatos {
    private ArrayList<String> provincias;
    private ArrayList<String> profesions;
    private ArrayList<Traballador> traballadores;

    public ModeloDatos() {
        provincias = new ArrayList<>();
        profesions = new ArrayList<>();
        traballadores = new ArrayList<>();
    }

    public ArrayList<String> getProvincias() {
        return provincias;
    }

    public ArrayList<String> getProfesions() {
        return profesions;
    }

    public ArrayList<Traballador> getTraballadores() {
        return traballadores;
    }

    public boolean engadirProvincia(String provincia) {
        if (!provincias.contains(provincia)) {
            provincias.add(provincia);
            return true;
        }
        return false;
    }

    public boolean eliminarProvincia(String provincia) {
        for (Traballador t : traballadores) {
            if (t.getProvincia().equals(provincia)) {
                return false; // No se puede eliminar si hay trabajadores en esa provincia
            }
        }
        return provincias.remove(provincia);
    }

    public boolean engadirProfesion(String profesion) {
        if (!profesions.contains(profesion)) {
            profesions.add(profesion);
            return true;
        }
        return false;
    }

    public boolean eliminarProfesion(String profesion) {
        for (Traballador t : traballadores) {
            if (t.getProfesion().equals(profesion)) {
                return false; // No se puede eliminar si hay trabajadores con esa profesión
            }
        }
        return profesions.remove(profesion);
    }

    public boolean engadirTraballador(Traballador traballador) {
        for (Traballador t : traballadores) {
            if (t.getDni().equals(traballador.getDni())) {
                return false; // No se pueden repetir DNIs
            }
        }
        traballadores.add(traballador);
        return true;
    }
}