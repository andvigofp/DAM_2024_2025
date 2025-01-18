/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class Ejercicio2_Calculadora_Iteracion extends javax.swing.JFrame{
    private JTextField txtRadio;
    private JTextField txtAltura;
    private JLabel lblArea;
    private JLabel lblVolumen;
    
    public Ejercicio2_Calculadora_Iteracion() {
        
      // Configuración del JFrame
        setTitle("Calculadora de Cilindro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(null); // Sin layout manager

        //Crear JPanel y configurar sin Layout manager
        JPanel panel = new JPanel();
        panel.setLayout(null); // No usar LayoutManager
        panel.setBounds(0, 0, 350, 350);
        
        // Creación y posicionamiento de componentes
        JLabel lblRadio = new JLabel("Ingrese el radio:");
        lblRadio.setBounds(20, 20, 120, 25);
        panel.add(lblRadio);

        txtRadio = new JTextField();
        txtRadio.setBounds(150, 20, 150, 25);
        panel.add(txtRadio);

        JLabel lblAltura = new JLabel("Ingrese la altura:");
        lblAltura.setBounds(20, 60, 120, 25);
        panel.add(lblAltura);

        txtAltura = new JTextField();
        txtAltura.setBounds(150, 60, 150, 25);
        panel.add(txtAltura);

        JLabel lblAreaText = new JLabel("El área es:");
        lblAreaText.setBounds(20, 100, 120, 25);
        panel.add(lblAreaText);

        lblArea = new JLabel("");
        lblArea.setBounds(150, 100, 150, 25);
        panel.add(lblArea);

        JLabel lblVolumenText = new JLabel("El volumen es:");
        lblVolumenText.setBounds(20, 140, 120, 25);
        panel.add(lblVolumenText);

        lblVolumen = new JLabel("");
        lblVolumen.setBounds(150, 140, 150, 25);
        panel.add(lblVolumen);

        JButton btnCalcular = new JButton("calcular");
        btnCalcular.setBounds(50, 180, 100, 30);
        panel.add(btnCalcular);

        JButton btnReiniciar = new JButton("reiniciar");
        btnReiniciar.setBounds(180, 180, 100, 30);
        panel.add(btnReiniciar);
        
        JButton btnAceptar = new JButton("Ok");
        btnAceptar.setBounds(85, 230, 75, 25);
        panel.add(btnAceptar);
        
        JButton btnCancelar = new JButton("Cancer");
        btnCancelar.setBounds(170, 230, 75, 25);
        panel.add(btnCancelar);
        
        //Añadir el panel al JFrame
        add(panel);
        
       
        setVisible(true);
        
        

        // Acciones de los botones
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });

        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciar();
            }
        });
        
         
         // Acciones de los botones
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        
        
        // Acciones de los botones
         btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptar();
            }
        });
        
    }
    

    private void calcular() {
        try {
            double radio = Double.parseDouble(txtRadio.getText());
            double altura = Double.parseDouble(txtAltura.getText());

            // Cálculo del área lateral y volumen de un cilindro
            double area = 2 * Math.PI * radio * (radio + altura);
            double volumen = Math.PI * Math.pow(radio, 2) * altura;

            // Mostrar resultados en las etiquetas
            lblArea.setText(String.format("%.2f", area));
            lblVolumen.setText(String.format("%.2f", volumen));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reiniciar() {
        // Limpiar campos de texto y etiquetas de resultados
        txtRadio.setText("");
        txtAltura.setText("");
        lblArea.setText("");
        lblVolumen.setText("");
    }
    
    
     private void cancelar() {
         System.exit(0);
    }
     
    
      private void aceptar() {
        CalculadoraBasica calculadora = new CalculadoraBasica();
        calculadora.setVisible(true);
        this.dispose();
    } 
     

    public static void main(String[] args) {
        new Ejercicio2_Calculadora_Iteracion().setVisible(true);
    }
}
