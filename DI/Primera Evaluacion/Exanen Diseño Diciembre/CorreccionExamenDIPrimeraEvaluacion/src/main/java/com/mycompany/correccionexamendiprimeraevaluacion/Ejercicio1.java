/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.correccionexamendiprimeraevaluacion;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author PcVIP
 */
public class Ejercicio1 extends JFrame{
    public Ejercicio1() {
        setTitle("Menú Xestion Alumnado");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 600, 400);
        add(panel);
        
        JMenuBar barraMenu = new JMenuBar();
        
        JMenu menuInicio = new JMenu("Inciio");
        JMenu menuXestionAlumando = new JMenu("Xestión Alumnado");
        JMenu menuProfesorado = new JMenu("Profesorado");
        JMenu menuNonDocente = new JMenu("Non Docente");
        
        JMenu menuMatricula = new JMenu("Matricula");
        
        JMenuItem matriculaDirecta = new JMenuItem("Matrícula Directa");
        JMenuItem matriculaListaxe = new JMenuItem("Matrícula Listaxe");
        menuMatricula.add(matriculaDirecta);
        menuMatricula.add(matriculaListaxe);

        // Opciones adicionales en "Xestión Alumnado"
        JMenuItem AdminisionAlumno = new JMenuItem("Admisión alumnado PL");
        JMenuItem grupo = new JMenuItem("Grupo");
        
        JMenuItem actividade = new JMenuItem("Actividade");
        JMenuItem historicoAlumando = new JMenuItem("Historico alumando");

        // Añadir submenús y elementos al menú "Xestión Alumnado"
        menuXestionAlumando.add(menuMatricula);
        menuXestionAlumando.add(AdminisionAlumno);
        menuXestionAlumando.add(grupo);
        menuXestionAlumando.add(actividade);
        menuXestionAlumando.add(historicoAlumando);

        // Añadir menús principales a la barra
        barraMenu.add(menuInicio);
        barraMenu.add(menuXestionAlumando);
        barraMenu.add(menuProfesorado);
        barraMenu.add(menuNonDocente);

        // Asignar la barra de menú al JFrame
        setJMenuBar(barraMenu);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ejercicio1();
    }
}
