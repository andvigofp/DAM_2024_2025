/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercico5;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author andri
 */
public class Ejercicio2_Calculadora extends javax.swing.JFrame{
    
    public Ejercicio2_Calculadora() {
        
        //Configurar el JFRame
        setTitle("Calculadora de Cilindro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 350);
        setLocationRelativeTo(null);
        
        //Crear JPanel y configurar sin Layout manager
        JPanel panel = new JPanel();
        panel.setLayout(null); // No usar LayoutManager
        panel.setBounds(0, 0, 350, 350);
        
        //Añadimos los componenentes al panel
        JLabel lbRadio = new JLabel("Ingrese el radio:");
        lbRadio.setBounds(20, 20, 120, 25);
        panel.add(lbRadio);
        
        JTextField txRadio = new JTextField();
        txRadio.setBounds(150, 20, 150, 25);
        panel.add(txRadio);
        
        JLabel lbAltura = new JLabel("Ingrese la altura:");
        lbAltura.setBounds(20, 45, 120, 25);
        panel.add(lbAltura);
        
        JTextField txtAltura = new JTextField();
        txtAltura.setBounds(150, 45, 150, 25);
        panel.add(txtAltura);
        
        JLabel lbAreaText = new JLabel("El área es:");
        lbAreaText.setBounds(20, 75, 120, 25);
        panel.add(lbAreaText);
        
        JLabel lbArea = new JLabel("");
        lbArea.setBounds(150, 100, 150, 25);
        panel.add(lbArea);
        
        JLabel lblVolumenText = new JLabel("El volumen es:");
        lblVolumenText.setBounds(20, 100, 120, 25);
        panel.add(lblVolumenText);
        
        JLabel lblVolumen = new JLabel("");
        lblVolumen.setBounds(150, 25, 150, 25);
        panel.add(lblVolumen);
        
        JButton btnCalcular = new JButton("calcular");
        btnCalcular.setBounds(50, 130, 120, 20);
        panel.add(btnCalcular);
        
        JButton btnReiniciar = new JButton("reiniciar");
        btnReiniciar.setBounds(150, 130, 150, 20);
        panel.add(btnReiniciar);
        
        JButton btnAceptar = new JButton("Ok");
        btnAceptar.setBounds(85, 230, 75, 25);
        panel.add(btnAceptar);
        
        JButton btnCanclear = new JButton("Cancer");
        btnCanclear.setBounds(170, 230, 75, 25);
        panel.add(btnCanclear);
        
        //Añadir el panel al JFrame
        add(panel);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Ejercicio2_Calculadora();
    }
}
