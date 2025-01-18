/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercico5;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author andri
 */
public class Ejercico1_Botones extends javax.swing.JFrame {
    private JButton button1, button2;
    
   public Ejercico1_Botones() {
       setLayout(null);
       setTitle("Ejemplo con Layout a null");
       getContentPane().setBackground(Color.WHITE);
       
       button1 = new JButton("Rojo");
       button1.setBounds(150, 150, 100, 100);
       button1.setForeground(Color.WHITE);
       button1.setBackground(Color.red);
       add(button1);
       
       button2 = new JButton("Azul");
       button2.setBounds(350, 150, 100, 100);
       button2.setForeground(Color.WHITE);
       button2.setBackground(Color.BLUE);
       add(button2);
       
        // Acciones de los botones
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rojo();
            }

           private void rojo() {
              getContentPane().setBackground(Color.red);
           }
        });
        
        
         button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                azul();
            }

           private void azul() {
              getContentPane().setBackground(Color.blue);
           }
        });
   }
   
    public static void main(String[] args) {
        Ejercico1_Botones botones = new Ejercico1_Botones();
        botones.setBounds(0, 0, 600, 600);
        botones.setLocationRelativeTo(null);
        botones.setDefaultCloseOperation(EXIT_ON_CLOSE);
        botones.setVisible(true);
    }
}
