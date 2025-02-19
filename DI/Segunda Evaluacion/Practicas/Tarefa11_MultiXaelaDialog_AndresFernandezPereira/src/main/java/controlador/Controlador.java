/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.ModeloDatos;
import modelo.Traballador;
import vista.*;

import javax.swing.JOptionPane;
/**
 *
 * @author Andrés Fernández Pereira
 */


public class Controlador {
    private ModeloDatos modelo;
    private Frameppal frameppal;

    public Controlador() {
        this(false);
    }

    //Constructor para abrir ventana princial
    public Controlador(boolean showFrameppal) {
        modelo = new ModeloDatos();
        if (showFrameppal) {
            frameppal = new Frameppal(this);
            frameppal.setVisible(true);
        }
    }

    //Método para poder abrir la ventana XestionProvincias
    public void abrirVistaXestionProvincias() {
        VistaXestionProvincias vista = new VistaXestionProvincias(this);
        vista.setVisible(true);
    }

    //Método para poder abrir la ventana XestionProfesións
    public void abrirVistaXestionProfesions() {
        VistaXestionProfesions vista = new VistaXestionProfesions(this);
        vista.setVisible(true);
    }

    //Método para poder abrir la ventana AltaTraballador
    public void abrirVistaAltaTraballador() {
        VistaAltaTraballador vista = new VistaAltaTraballador(this);
        vista.setVisible(true);
    }

    //Método para poder abrir la ventana TraballadoresDisponibles
    public void abrirVistaTraballadoresDisponibles() {
        VistaTraballadoresDisponibles vista = new VistaTraballadoresDisponibles(this);
        vista.setVisible(true);
    }

   // Método para obtener los trabajadores disponibles
    public List<Traballador> getTraballadoresDisponibles() {
        return modelo.getTraballadores();
    }

    // Método para obtener las profesiones disponibles
    public List<String> getProfesions() {
        return modelo.getProfesions();
    }
    
    // Método para obtener las provincias disponibles
    public List<String> getProvincias() {
        return modelo.getProvincias();
    }

    //Método para engadir las provincias
    public void engadirProvincia(String provincia) {
        if (modelo.engadirProvincia(provincia)) {
            JOptionPane.showMessageDialog(null, "Provincia engadida correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "A provincia xa existe");
        }
    }

    //Método para eliminar la provincia
    public void eliminarProvincia(String provincia) {
        if (modelo.eliminarProvincia(provincia)) {
            JOptionPane.showMessageDialog(null, "Provincia eliminada correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Non se pode eliminar esta provincia porque ten traballadores");
        }
    }

    //Método para engadir las profesión
    public void engadirProfesion(String profesion) {
        if (modelo.engadirProfesion(profesion)) {
            JOptionPane.showMessageDialog(null, "Profesión engadida correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "A profesión xa existe");
        }
    }

    //Método para eliminar la profesión
    public void eliminarProfesion(String profesion) {
        if (modelo.eliminarProfesion(profesion)) {
            JOptionPane.showMessageDialog(null, "Profesión eliminada correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Non se pode eliminar esta profesión porque ten traballadores");
        }
    }

    //Método para engadir un nuevo traballador
   public void engadirTraballador(String dni, String nome, String apelido1, String apelido2, String provincia, String profesion) {
    Traballador traballador = new Traballador(dni, nome, apelido1, apelido2, provincia, profesion);
    if (modelo.engadirTraballador(traballador)) {
        JOptionPane.showMessageDialog(null, "Traballador engadido correctamente");
    } else {
        JOptionPane.showMessageDialog(null, "Xa existe un traballador con ese DNI");
    }
  }
}
