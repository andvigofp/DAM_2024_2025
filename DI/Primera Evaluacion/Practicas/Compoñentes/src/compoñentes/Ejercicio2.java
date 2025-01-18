/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compoñentes;

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
public class Ejercicio2 extends javax.swing.JFrame{
    private JTextField txtNum1;
    private JTextField txtNum2;
    private JLabel lblResultado;
    
    public Ejercicio2() {
         setTitle("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 350);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 350);
        
        // Campo para el primer número
        JLabel labelNumero1 = new JLabel("Número 1:");
        labelNumero1.setBounds(50, 30, 80, 30);
        panel.add(labelNumero1);

        JTextField textNumero1 = new JTextField();
        textNumero1.setBounds(140, 30, 150, 30);
        panel.add(textNumero1);

        // Campo para el segundo número
        JLabel labelNumero2 = new JLabel("Número 2:");
        labelNumero2.setBounds(50, 80, 80, 30);
        panel.add(labelNumero2);

        JTextField textNumero2 = new JTextField();
        textNumero2.setBounds(140, 80, 150, 30);
        panel.add(textNumero2);

        // Botones para seleccionar la operación
        JButton botonSumar = new JButton("Sumar");
        botonSumar.setBounds(50, 130, 100, 30);
        panel.add(botonSumar);

        JButton botonMultiplicar = new JButton("Multiplicar");
        botonMultiplicar.setBounds(190, 130, 100, 30);
        panel.add(botonMultiplicar);

        // Botón para reiniciar
        JButton botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.setBounds(50, 180, 100, 30);
        panel.add(botonReiniciar);

        // Resultado
        JLabel labelResultado = new JLabel("Resultado: ");
        labelResultado.setBounds(50, 230, 300, 30);
        panel.add(labelResultado);
        
         // Crear botón "Limpiar Campos"
        JButton limpButton = new JButton("Limpiar");
        limpButton.setBounds(190, 180, 100, 30); // Ubicación del botón
        panel.add(labelNumero1);
        
        limpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               txtNum1.setText("");
               txtNum2.setText("");
            }
        });
        

        // Acción del botón Sumar
        botonSumar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numero1 = Double.parseDouble(textNumero1.getText());
                    double numero2 = Double.parseDouble(textNumero2.getText());
                    double resultado = numero1 + numero2;
                    labelResultado.setText("Resultado: " + resultado);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón Multiplicar
        botonMultiplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double numero1 = Double.parseDouble(textNumero1.getText());
                    double numero2 = Double.parseDouble(textNumero2.getText());
                    double resultado = numero1 * numero2;
                    labelResultado.setText("Resultado: " + resultado);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción del botón Reiniciar
        botonReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textNumero1.setText(""); // Limpiar el campo número 1
                textNumero2.setText(""); // Limpiar el campo número 2
                labelResultado.setText("Resultado: "); // Reiniciar el resultado
            }
        });
        
       
        
        add(panel);

        setVisible(true);
    }

  
    public static void main(String[] args) {
        new Ejercicio2();
    }   
}
