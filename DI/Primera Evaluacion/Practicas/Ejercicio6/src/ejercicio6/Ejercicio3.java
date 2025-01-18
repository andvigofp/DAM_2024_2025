/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author andri
 */
public class Ejercicio3 extends javax.swing.JFrame{
    private ArrayList<String> libros; // ArrayList para almacenar los títulos
    
    public Ejercicio3() {
    // Configuración de la ventana
        setTitle("Biblioteca");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Inicializar el ArrayList
        libros = new ArrayList<>();

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(null); // Usamos layout nulo para organizar manualmente los componentes
        add(panel);

        // Menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem limpiarCampos = new JMenuItem("Nuevo Libro");
        JMenuItem mostrarLibros = new JMenuItem("Mostrar Libros");
        JMenuItem salir = new JMenuItem("Salir");

        menu.add(limpiarCampos);
        menu.add(mostrarLibros);
        menu.add(salir);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Campo de texto para el título del libro
        JLabel labelTitulo = new JLabel("Título del libro:");
        labelTitulo.setBounds(30, 30, 120, 30);
        panel.add(labelTitulo);

        JTextField textTitulo = new JTextField();
        textTitulo.setBounds(150, 30, 200, 30);
        panel.add(textTitulo);

        // Botón para añadir el libro
        JButton botonAñadir = new JButton("Añadir Libro");
        botonAñadir.setBounds(50, 80, 120, 30);
        panel.add(botonAñadir);
        
          // Crear botón "Volver a Ejercicio4"
        JButton volverButton = new JButton("Ejericio 4");
        volverButton.setBounds(200, 80, 120, 30); // Ubicación del botón
        panel.add(volverButton);
        
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual (Ejercicio1)
                new Ejercicio6(); // Abre Ejercicio4
            }
        });

        // Acción para "Nuevo Libro"
        limpiarCampos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textTitulo.setText(""); // Limpiar el campo de texto
            }
        });

        // Acción para "Añadir Libro"
        botonAñadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = textTitulo.getText().trim(); // Obtener el texto del campo
                if (titulo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    libros.add(titulo); // Añadir el título al ArrayList
                    JOptionPane.showMessageDialog(null, "Libro añadido: " + titulo, "Información", JOptionPane.INFORMATION_MESSAGE);
                    textTitulo.setText(""); // Limpiar el campo después de añadir
                }
            }
        });

        // Acción para "Mostrar Libros"
        mostrarLibros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (libros.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay libros almacenados.", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder listaLibros = new StringBuilder("Libros en la biblioteca:\n");
                    for (String libro : libros) {
                        listaLibros.append("- ").append(libro).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, listaLibros.toString(), "Lista de Libros", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Acción para "Salir"
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Salir de la aplicación
            }
        });

        // Mostrar la ventana
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Ejercicio3();
    }
}
