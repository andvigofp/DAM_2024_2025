/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ejercico5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author andri
 */
public class CalculadoraBasica extends javax.swing.JFrame {
    
    private JTextField txtNum1;
    private JTextField txtNum2;
    private JLabel lblResultado;
    
    public CalculadoraBasica() {
        // Configuración del JFrame
        setTitle("Calculadora Básica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null); // Sin layout manager

        // Crear JPanel y configurar sin Layout manager
        JPanel panel = new JPanel();
        panel.setLayout(null); // No usar LayoutManager
        panel.setBounds(0, 0, 350, 350);
        
        // Creación y posicionamiento de componentes
        JLabel lblNum1 = new JLabel("Ingrese el primer número:");
        lblNum1.setBounds(20, 20, 150, 25);
        panel.add(lblNum1);

        txtNum1 = new JTextField();
        txtNum1.setBounds(180, 20, 150, 25);
        panel.add(txtNum1);

        JLabel lblNum2 = new JLabel("Ingrese el segundo número:");
        lblNum2.setBounds(20, 60, 150, 25);
        panel.add(lblNum2);

        txtNum2 = new JTextField();
        txtNum2.setBounds(180, 60, 150, 25);
        panel.add(txtNum2);

        JLabel lblResultadoText = new JLabel("Resultado:");
        lblResultadoText.setBounds(20, 100, 120, 25);
        panel.add(lblResultadoText);

        lblResultado = new JLabel("");
        lblResultado.setBounds(150, 100, 150, 25);
        panel.add(lblResultado);

        // Botones para las operaciones
        JButton btnSumar = new JButton("Sumar");
        btnSumar.setBounds(50, 140, 75, 30);
        panel.add(btnSumar);

        JButton btnRestar = new JButton("Restar");
        btnRestar.setBounds(140, 140, 75, 30);
        panel.add(btnRestar);

        JButton btnMultiplicar = new JButton("Multiplicar");
        btnMultiplicar.setBounds(137, 180, 100, 30);
        panel.add(btnMultiplicar);

        JButton btnDividir = new JButton("Dividir");
        btnDividir.setBounds(50, 180, 75, 30);
        panel.add(btnDividir);

        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setBounds(100, 235, 100, 30);
        panel.add(btnReiniciar);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(210, 235, 100, 30);
        panel.add(btnVolver);

        // Añadir el panel al JFrame
        add(panel);
        
        setVisible(true);

        // Acción para el botón de sumar
        btnSumar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("sumar");
            }
        });

        // Acción para el botón de restar
        btnRestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("restar");
            }
        });

        // Acción para el botón de multiplicar
        btnMultiplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("multiplicar");
            }
        });

        // Acción para el botón de dividir
        btnDividir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarOperacion("dividir");
            }
        });

        // Acción para reiniciar los campos
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciar();
            }
        });

        
         // Acciones de los botones
         btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });
    }
    
    

    private void realizarOperacion(String operacion) {
        try {
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double resultado = 0;

            // Realizar la operación según el botón presionado
            switch (operacion) {
                case "sumar":
                    resultado = num1 + num2;
                    break;
                case "restar":
                    resultado = num1 - num2;
                    break;
                case "multiplicar":
                    resultado = num1 * num2;
                    break;
                case "dividir":
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(this, "No se puede dividir por cero.", "Error", JOptionPane.ERROR_MESSAGE);
                        txtNum2.setText("");
                        return;
                    }
                    resultado = num1 / num2;
                    break;
            }

            // Mostrar el resultado en la etiqueta
            lblResultado.setText(String.format("%.2f", resultado));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reiniciar() {
        // Limpiar campos de texto y el resultado
        txtNum1.setText("");
        txtNum2.setText("");
        lblResultado.setText("");
    }

  
    
     private void volver() {
        Ejercicio2_Calculadora_Iteracion calculadora = new Ejercicio2_Calculadora_Iteracion();
        calculadora.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new CalculadoraBasica().setVisible(true);
    }
}
