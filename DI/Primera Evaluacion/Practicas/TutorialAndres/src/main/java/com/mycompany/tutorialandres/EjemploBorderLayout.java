/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tutorialandres;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 *
 * @author a13andresfp
 */
public class EjemploBorderLayout extends javax.swing.JFrame{

    
    public EjemploBorderLayout() {
        JFrame frame = new JFrame("Ejemplo BorderLayaout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        frame.setLayout(new BorderLayout());
        
         JButton botonNorte=new JButton("Norte");
         frame.add(botonNorte,BorderLayout.NORTH);
         
         JButton botonSur=new JButton("Sur");
         frame.add(botonSur,BorderLayout.SOUTH);
         
         JButton botonEste=new JButton("Este");
         frame.add(botonEste,BorderLayout.EAST);
         
         JButton botonOeste=new JButton("Oeste");
         frame.add(botonOeste,BorderLayout.WEST);
         
         JButton botonCentro=new JButton("Centro");
         frame.add(botonCentro, BorderLayout.CENTER);
         
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        EjemploBorderLayout ejemplo = new EjemploBorderLayout();
        ejemplo.add(ejemplo);
    }
    
}
