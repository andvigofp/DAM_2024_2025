/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorialandres;
import java.awt.Color;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JButton;
/**
 *
 * @author a13andresfp
 */
public class EjemploBotones extends javax.swing.JFrame {
    private JButton button1, button2;
    
    public EjemploBotones() {
        setLayout(null);
        setTitle("Botones Colores");
        getContentPane().setBackground(Color.WHITE);    
        
        button1 = new JButton("Rojo");
        button1.setBounds(150, 150, 100, 100);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.red);
        add(button1);
        
        button2 = new JButton("Rojo");
        button2.setBounds(300, 150, 100, 100);
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLUE);
        add(button2);
        
        
    
          
    }
    public static void main(String[] args) {
        EjemploBotones botones = new EjemploBotones();
        botones.setBounds(0, 0, 600, 600);
        botones.setLocationRelativeTo(null);
        botones.setDefaultCloseOperation(EXIT_ON_CLOSE);
        botones.setVisible(true);
    }
}
