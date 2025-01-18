/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.eventosteclado;

import javax.swing.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class EventosTeclado extends JFrame {

    private JTextArea entradaTexto;
    private JTextArea salidaTexto;
    private JLabel etiquetaVocales;
    private int contadorVocales = 0;

    public EventosTeclado() {
        // Configurar ventana
        setTitle("Eventos del Teclado");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Sin LayoutManager

        // Etiqueta título
        JLabel titulo = new JLabel("Eventos del Teclado");
        titulo.setFont(titulo.getFont().deriveFont(24f));
        titulo.setBounds(150, 10, 300, 30);
        add(titulo);

        // Área de entrada de texto
        entradaTexto = new JTextArea();
        entradaTexto.setBorder(BorderFactory.createTitledBorder("Área Entrada de Texto"));
        entradaTexto.setBounds(50, 50, 400, 100);
        add(entradaTexto);

        // Área de salida de texto
        salidaTexto = new JTextArea();
        salidaTexto.setEditable(false);
        salidaTexto.setBorder(BorderFactory.createTitledBorder("Área Salida de Texto"));
        salidaTexto.setBounds(50, 160, 400, 100);
        add(salidaTexto);

        // Etiqueta para contar vocales
        etiquetaVocales = new JLabel("Número de Vocales: 0");
        etiquetaVocales.setBounds(300, 270, 150, 30);
        add(etiquetaVocales);

        // Mensaje inferior
        JLabel mensajeSalir = new JLabel("Para salir presione la tecla Escape...");
        mensajeSalir.setBounds(50, 270, 250, 30);
        add(mensajeSalir);

        // Añadir KeyListener al área de entrada
        entradaTexto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Soltó la tecla: " + e.getKeyChar());
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if ("aeiouAEIOU".indexOf(keyChar) != -1) {
                    salidaTexto.append(String.valueOf(keyChar).toLowerCase() + " ");
                    contadorVocales++;
                    etiquetaVocales.setText("Número de Vocales: " + contadorVocales);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    int confirm = JOptionPane.showConfirmDialog(
                        EventosTeclado.this,
                        "¿Estás seguro de que deseas salir?",
                        "Salir",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        new EventosTeclado();
    }
}
